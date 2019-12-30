package com.roman.matrix.reader;

import com.roman.matrix.model.Matrix;
import org.junit.jupiter.api.Test;

import java.io.Reader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FormattedReaderTest {

    @Test
    void read_correctInputStream_matchingMatrix() {

        String matrixAsString =
                "5,6,7.8\n" +
                        "7,79.7,11";
        double[][] matrixAsDoubles = {
                {5, 6, 7.8},
                {7, 79.7, 11}
        };

        Reader stream = new StringReader(matrixAsString);
        FormattedReader reader = new FormattedReader(stream, ",");
        Matrix correctMatrix = new Matrix(matrixAsDoubles);

        Matrix matrix = reader.read();

        assertEquals(correctMatrix, matrix);
    }
}