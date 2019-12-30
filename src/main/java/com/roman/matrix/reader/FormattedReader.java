package com.roman.matrix.reader;

import com.roman.matrix.model.Matrix;
import com.roman.matrix.utils.StringUtils;

import java.io.BufferedReader;
import java.io.Reader;

public class FormattedReader extends AbstractReader {

    private Reader reader;
    private String separator;

    public FormattedReader(Reader reader, String separator) {
        this.reader = reader;
        this.separator = separator;
    }

    @Override
    public Matrix read() {

        BufferedReader reader = new BufferedReader(this.reader);

        double[][] matrixArray = reader.lines()
                .map(line -> line.split(separator))
                .map(StringUtils::convertToLongArray)
                .toArray(double[][]::new);

        return new Matrix(matrixArray);
    }
}
