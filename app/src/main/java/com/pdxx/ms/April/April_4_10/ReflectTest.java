package com.pdxx.ms.April.April_4_10;

/**
 * Created by Administrator on 2018/4/10.
 */

public class ReflectTest {
    public String man;
    public String women;
    private String son;

    public ReflectTest(){
        SysUtil.sys("public无参构造方法");
    }
    public ReflectTest(String man, String women) {
        this.man = man;
        this.women = women;
        SysUtil.sys("public有参构造方法");
    }

    private ReflectTest(String man, String women, String son) {
        this.man = man;
        this.women = women;
        this.son = son;
        SysUtil.sys("private有参构造方法");
    }

    public void reflectMan() {
        SysUtil.sys(man);
    }

    private void reflectWomen() {
        SysUtil.sys("testwomen");
    }

    @Override
    public String toString() {
        return "ReflectTest{" +
                "man='" + man + '\'' +
                ", women='" + women + '\'' +
                ", son='" + son + '\'' +
                '}';
    }
}
