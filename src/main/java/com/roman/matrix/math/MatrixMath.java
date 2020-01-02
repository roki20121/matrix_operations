package com.roman.matrix.math;

import com.roman.matrix.model.Matrix;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;
import java.util.function.IntConsumer;

public class MatrixMath {

    private static ExecutorService threadPool = Executors.newCachedThreadPool(); // shared threadPool

    /**
     * @param matrix1 first Matrix
     * @param matrix2 second Matrix
     * @return new Matrix, result of addition of two arguments
     */
    public static Matrix addMatrices(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getNumberOfRows() != matrix2.getNumberOfRows() ||
                matrix1.getNumberOfColumns() != matrix2.getNumberOfColumns()) {
            throw new IllegalArgumentException("Matrices have to have the same size!");
        }

        Matrix copy = new Matrix(matrix1);
        CountDownLatch latch = new CountDownLatch(copy.getNumberOfRows());

        IntConsumer rowAdder = n -> {
            for (int i = 0; i < copy.getNumberOfColumns(); i++) {
                double newVal = copy.getCell(n, i) + matrix2.getCell(n, i);
                copy.setCell(n, i, newVal);
            }
        };


        for (int i = 0; i < copy.getNumberOfRows(); i++) {
            final int row = i;
            threadPool.submit(() -> {
                rowAdder.accept(row);
                latch.countDown();
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return copy;
    }

    /**
     * @param matrix1 first Matrix
     * @param matrix2 second Matrix
     * @return new Matrix, result of multiplication of two arguments
     */
    public static Matrix multiplyMatrices(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getNumberOfColumns() != matrix2.getNumberOfRows()) {
            throw new IllegalArgumentException(
                    "Number of columns in first matrix has to be equal to number of rows in second");
        }

        int cellsInResult = matrix1.getNumberOfRows() * matrix2.getNumberOfColumns();
        Matrix result = new Matrix(matrix1.getNumberOfRows(), matrix2.getNumberOfColumns());

        CountDownLatch latch = new CountDownLatch(cellsInResult);

        BiConsumer<Integer, Integer> multiplier = (m, n) -> {
            double sum = 0;
            for (int i = 0; i < matrix1.getNumberOfColumns(); i++) {
                sum += matrix1.getCell(m, i) * matrix2.getCell(i, n);
            }
            result.setCell(m, n, sum);
        };

        for (int i = 0; i < result.getNumberOfRows(); i++) {
            final int row = i;
            for (int j = 0; j < result.getNumberOfColumns(); j++) {
                final int column = j;
                threadPool.submit(() -> {
                    multiplier.accept(row, column);
                    latch.countDown();
                });
            }
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

}
