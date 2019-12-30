package com.roman.matrix.model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatrixTest {

    @Test
    void toString_nonSquareMatrix_correctNumberOfLines() {

        int m = 3;
        int n = 4;
        Matrix matrix = new Matrix(m, n);

        String matrixAsString = matrix.toString();

        int lines = matrixAsString.length() - matrixAsString.replace("\n", "").length() + 1;
        assertEquals(m, lines);

    }
}