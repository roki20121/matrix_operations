package com.roman.matrix.math;

import com.roman.matrix.TestUtils;
import com.roman.matrix.model.Matrix;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class MatrixMathTest {

    @ParameterizedTest
    @MethodSource("generateSquareMatrices")
    void addMatrices_smallMatrices_correctResult(Matrix matrix1, Matrix matrix2) {


        Matrix correctMatrix = addMatrices(matrix1, matrix2);

        Matrix result = MatrixMath.addMatrices(matrix1, matrix2);

        assertNotSame(result, matrix1);
        assertNotSame(result, matrix2);
        assertEquals(correctMatrix, result);

    }


    private static Stream<Arguments> generateSquareMatrices() throws IOException {

        Matrix matrix1 = TestUtils.readMatrixFromResource("1/1.csv");
        Matrix matrix2 = TestUtils.readMatrixFromResource("1/2.csv");

        Matrix matrix3 = TestUtils.readMatrixFromResource("5/1.csv");
        Matrix matrix4 = TestUtils.readMatrixFromResource("5/2.csv");

        Matrix matrix5 = TestUtils.readMatrixFromResource("10/1.csv");
        Matrix matrix6 = TestUtils.readMatrixFromResource("10/2.csv");

        Matrix matrix7 = TestUtils.readMatrixFromResource("100/1.csv");
        Matrix matrix8 = TestUtils.readMatrixFromResource("100/2.csv");

//        Matrix matrix9 = new FormattedReader(new FileReader("1000/1.csv"),"\t").read();
//        Matrix matrix10 = new FormattedReader(new FileReader("1000/2.csv"), "\t").read();

        return Stream.of(
                Arguments.of(matrix1, matrix2),
                Arguments.of(matrix3, matrix4),
                Arguments.of(matrix5, matrix6),
                Arguments.of(matrix7, matrix8)
//                Arguments.of(matrix9,matrix10)
        );
    }

    @ParameterizedTest
    @MethodSource("generateSquareMatrices")
    void multiplyMatrices_variousMatirces_correctResult(Matrix matrix1, Matrix matrix2) {
        Matrix correctMatrix = multMatrices(matrix1, matrix2);

        Matrix computed = MatrixMath.multiplyMatrices(matrix1, matrix2);

        assertEquals(correctMatrix, computed);
    }

    @Test
    void multiplyMatrices_smallMatrices_correctResult() {
        double[][] firstArray = new double[][]{
                {876.0272507, 994.7061238, 773.0530224, 991.0397068, 8.413118898},
                {589.3680449, 377.082443, 229.6070181, 754.6477813, 352.3342221},
                {398.1895507, 265.3985274, 780.0715834, 975.9723523, 114.2894096},
                {941.434876, 851.4594552, 831.6183252, 367.5269975, 497.371386},
                {537.816248, 391.1969589, 857.9332515, 348.2839338, 303.2015422}
        };

        double[][] secondArray = new double[][]{
                {33.46755639, 361.10124, 725.9874523, 868.4790227, 689.5002495},
                {845.7020702, 351.6337918, 791.8467369, 780.3220707, 405.7338769},
                {375.9694304, 764.3112267, 647.2615962, 938.2293616, 104.7703038},
                {860.175828, 137.0211186, 973.0438912, 921.3782613, 808.6967729},
                {163.2181222, 295.4408616, 758.1532125, 342.0586192, 627.1850068}
        };

        Matrix matrix1 = new Matrix(firstArray);
        Matrix matrix2 = new Matrix(secondArray);

        Matrix correctMatrix = multMatrices(matrix1, matrix2);

        Matrix computed = MatrixMath.multiplyMatrices(matrix1, matrix2);

        assertEquals(correctMatrix, computed);

    }

    private Matrix multMatrices(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getNumberOfColumns() != matrix2.getNumberOfRows()) {
            throw new IllegalArgumentException(
                    "Number of columns in first matrix has to be equal to number of rows in second");
        }

        Matrix result = new Matrix(matrix1.getNumberOfRows(), matrix2.getNumberOfColumns());


        for (int i = 0; i < result.getNumberOfRows(); i++) {
            for (int j = 0; j < result.getNumberOfColumns(); j++) {
                double sum = 0;
                for (int k = 0; k < matrix1.getNumberOfColumns(); k++) {
                    sum += matrix1.getCell(i, k) * matrix2.getCell(k, j);
                }
                result.setCell(i, j, sum);
            }
        }

        return result;
    }

    private Matrix addMatrices(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getNumberOfColumns() != matrix1.getNumberOfRows() ||
                matrix2.getNumberOfColumns() != matrix2.getNumberOfRows() ||
                matrix1.getNumberOfColumns() != matrix2.getNumberOfRows()) {
            throw new IllegalArgumentException(
                    "Both matrices have to be squares and same size");
        }
        Matrix result = new Matrix(matrix1.getNumberOfRows(), matrix1.getNumberOfColumns());

        for (int i = 0; i < result.getNumberOfRows(); i++) {
            for (int j = 0; j < result.getNumberOfColumns(); j++) {
                double resValue = matrix1.getCell(i, j) + matrix2.getCell(i, j);
                result.setCell(i, j, resValue);
            }
        }
        return result;
    }

}