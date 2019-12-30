package com.roman.matrix.reader;

import com.roman.matrix.model.Matrix;

import java.io.IOException;

public abstract class AbstractReader {

    abstract Matrix read() throws IOException;

}
