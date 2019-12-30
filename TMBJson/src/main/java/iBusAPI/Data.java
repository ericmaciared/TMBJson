package iBusAPI;

import java.util.Arrays;

public class Data {
    private IBus[] ibus;

    @Override
    public String toString() {
        return "Data{" +
                "ibus=" + Arrays.toString(ibus) +
                '}';
    }

    public IBus[] getIbus() {
        return ibus;
    }
}
