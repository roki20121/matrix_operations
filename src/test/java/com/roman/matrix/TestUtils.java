package com.roman.matrix;

import com.roman.matrix.model.Matrix;
import com.roman.matrix.reader.FormattedReader;

import java.io.InputStream;
import java.io.InputStreamReader;

public class TestUtils {

    public static Matrix readMatrixFromResource(String resourcePath) {

        InputStream stream = TestUtils.class.getClassLoader().getResourceAsStream(resourcePath);

        InputStreamReader reader = new InputStreamReader(stream);

        return new FormattedReader(reader, "\t").read();
    }

}
