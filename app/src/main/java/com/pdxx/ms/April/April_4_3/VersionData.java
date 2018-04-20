package com.pdxx.ms.April.April_4_3;

/**
 * Created by Administrator on 2018/4/3.
 */

public class VersionData extends BaseData {
    public VersionEntity data;

    public static class VersionEntity {

        public String androidURL;
        public String androidVersion;
        public String iosURL;
        public String iosVersion;
        public String updateDesc;

        @Override
        public String toString() {
            return "VersionEntity{" +
                    "androidURL='" + androidURL + '\'' +
                    ", androidVersion='" + androidVersion + '\'' +
                    ", iosURL='" + iosURL + '\'' +
                    ", iosVersion='" + iosVersion + '\'' +
                    ", updateDesc='" + updateDesc + '\'' +
                    '}';
        }
    }

}
