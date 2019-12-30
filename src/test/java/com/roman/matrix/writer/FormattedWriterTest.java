package com.roman.matrix.writer;

import com.roman.matrix.model.Matrix;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FormattedWriterTest {

    @Test
    void write_smallMatrix_correctString() throws IOException {
        String matrixAsString =
                "5.0,6.0,7.8\n" +
                        "7.0,79.7,11.0";
        double[][] matrixAsDoubles = {
                {5, 6, 7.8},
                {7, 79.7, 11}
        };

        Writer writer = new StringWriter();
        FormattedWriter fWriter = new FormattedWriter(writer, ",");
        Matrix matrix = new Matrix(matrixAsDoubles);

        fWriter.write(matrix);

        assertEquals(matrixAsString, writer.toString());
    }
}