package com.roman.matrix.writer;

import com.roman.matrix.model.Matrix;

import java.io.IOException;
import java.io.Writer;

public class FormattedWriter extends AbstractWriter {

    private Writer writer;
    private String separator;

    public FormattedWriter(Writer writer, String separator) {
        this.writer = writer;
        this.separator = separator;
    }

    @Override
    public void write(Matrix matrix) throws IOException {
        for (int i = 0; i < matrix.getNumberOfRows(); i++) {
            for (int j = 0; j < matrix.getNumberOfColumns(); j++) {
                writer.write(Double.toString(matrix.getCell(i, j)));
                if (j < matrix.getNumberOfColumns() - 1) {
                    writer.write(separator);
                }
            }
            if (i < matrix.getNumberOfRows() - 1) {
                writer.write("\n");
            }
        }
    }
}
