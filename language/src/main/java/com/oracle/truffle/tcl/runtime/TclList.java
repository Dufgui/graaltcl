package com.oracle.truffle.tcl.runtime;

import java.util.List;
import java.util.Objects;

import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.library.ExportLibrary;

@ExportLibrary(InteropLibrary.class)
@SuppressWarnings("static-method")
public final class TclList implements TruffleObject {

    private final List<Object> values;

    public TclList(List<Object> values) {
        this.values = values;
    }

    public List<Object> getValues() {
        return values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TclList tclList = (TclList) o;
        return Objects.equals(values, tclList.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }

    @Override
    public String toString() {
        return "TclList{" + "values=" + values + '}';
    }
}
