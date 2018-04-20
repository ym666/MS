package com.pdxx.ms.MAR.MAR_3_13;

/**
 * Created by Administrator on 2018/3/13.
 */

public class ConstructorComputer {
    public static void main(String[] args) {
//        Computer.Builder builder = new Computer.Builder();
//        builder.setCpu("i7-7700").setGpu("gtx1080ti").setRam("16gb").setRom("1tb").create().show();

        Computer computer = new Computer();
        System.out.println(computer.toString());
    }
}
