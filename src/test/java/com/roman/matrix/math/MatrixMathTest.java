package com.roman.matrix.math;

import com.roman.matrix.model.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class MatrixMathTest {

    @Test
    void addMatrices_smallMatrices_correctResult() {
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

        double[][] correctResult = new double[firstArray.length][firstArray[0].length];
        for (int i = 0; i < firstArray.length; i++) {
            for (int j = 0; j < firstArray[i].length; j++) {
                correctResult[i][j] = firstArray[i][j] + secondArray[i][j];
            }
        }

        Matrix correctMatrix = new Matrix(correctResult);

        Matrix result = MatrixMath.addMatrices(matrix1, matrix2);

        assertNotSame(result, matrix1);
        assertNotSame(result, matrix2);
        assertEquals(correctMatrix, result);

    }

}