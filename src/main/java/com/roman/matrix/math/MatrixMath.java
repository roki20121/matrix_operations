package com.roman.matrix.math;

import com.roman.matrix.model.Matrix;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.IntConsumer;

public class MatrixMath {

    private static ExecutorService threadPool = Executors.newCachedThreadPool();

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


}
