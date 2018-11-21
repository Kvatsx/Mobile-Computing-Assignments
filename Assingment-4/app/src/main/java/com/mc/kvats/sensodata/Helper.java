package com.mc.kvats.sensodata;

public class Helper {

    public static Helper hp;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SensoData.db";

    // Table 1
    public static final String TABLE_NAME_1= "Accelerometer";
    public static final String COL_1_1 = "X";
    public static final String COL_1_2 = "Y";
    public static final String COL_1_3 = "Z";


    public static Helper getInstance() {
        if ( hp == null ) {
            hp = new Helper();
        }
        return hp;
    }
}