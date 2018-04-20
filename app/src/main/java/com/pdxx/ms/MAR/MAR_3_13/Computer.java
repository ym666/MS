package com.pdxx.ms.MAR.MAR_3_13;

import android.util.Log;

/**
 * Created by Administrator on 2018/3/13.
 */

public class Computer {
    private String cpu;
    private String gpu;
    private String rom;
    private String ram;

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", gpu='" + gpu + '\'' +
                ", rom='" + rom + '\'' +
                ", ram='" + ram + '\'' +
                '}';
    }

    public Computer() {
        this(new Builder());
    }

    public Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.gpu = builder.gpu;
        this.ram = builder.ram;
        this.rom = builder.rom;
    }

    public void show() {
        System.out.println(this.cpu + "---" + this.gpu + "---" + this.rom + "---" + this.ram);
    }


    public static final class Builder {
        private String cpu;
        private String gpu;
        private String rom;
        private String ram;

        public Builder() {

        }

        public Builder setCpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder setGpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public Builder setRom(String rom) {
            this.rom = rom;
            return this;
        }

        public Builder setRam(String ram) {
            this.ram = ram;
            return this;
        }

        public Computer create() {
            return new Computer(this);
        }
    }
}
