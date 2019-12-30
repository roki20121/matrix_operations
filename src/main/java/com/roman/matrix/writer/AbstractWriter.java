package com.roman.matrix.writer;

import com.roman.matrix.model.Matrix;

import java.io.IOException;

public abstract class AbstractWriter {

    public abstract void write(Matrix matrix) throws IOException;

}
