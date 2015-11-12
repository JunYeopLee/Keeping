package com.example.junyeop_imaciislab.keeping.util;

/**
 * Created by junyeop_imaciislab on 2015. 11. 10..
 */
public class Constant {
    private static final String serverURL = "http://166.104.142.94:62000/keeping_server";
    private static final String queryLogin = serverURL + "/user/login";
    private static final String queryUserInfo = serverURL + "/user/viewUserInfo";
    private static final String queryBloodInfo = serverURL + "/user/viewBloodInfo";
    private static String userId = "";

    public static String getServerURL() {
        return serverURL;
    }

    public static String getQueryLogin() {
        return queryLogin;
    }

    public static String getQueryUserInfo() {
        return queryUserInfo;
    }

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        Constant.userId = userId;
    }

    public static String getQueryBloodInfo() {
        return queryBloodInfo;
    }
}
