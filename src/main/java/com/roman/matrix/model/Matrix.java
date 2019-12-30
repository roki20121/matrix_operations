package com.roman.matrix.model;

import java.util.Arrays;

public class Matrix {

    private double[][] array;
    private int m, n;

    /**
     * @param m number of rows
     * @param n number of columns
     */
    public Matrix(int m, int n) {
        array = new double[m][n];
        this.m = m;
        this.n = n;
    }

    public Matrix(double[][] array) {
        this.array = array;
        m = array.length;
        if (m > 0) {
            n = array[0].length;
        }
    }

    public Matrix(Matrix matrix) {
        this.m = matrix.m;
        this.n = matrix.n;

        this.array = Arrays.copyOf(matrix.array, matrix.m);
    }

    /**
     * @param m index of row
     * @param n index of column
     * @return value in cell with given indexes
     */
    public double getCell(int m, int n) {
        return array[m][n];
    }

    /**
     * @param m   index of row
     * @param n   index of column
     * @param val new value to set
     */
    public void setCell(int m, int n, double val) {
        array[m][n] = val;
    }

    public int getNumberOfRows() {
        return m;
    }

    public int getNumberOfColumns() {
        return n;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < array.length; i++) {
            builder.append(Arrays.toString(array[i]));
            if (i < array.length - 1) {
                builder.append("\n");
            }
        }

        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix1 = (Matrix) o;
        return Arrays.deepEquals(array, matrix1.array);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(array);
    }
}
