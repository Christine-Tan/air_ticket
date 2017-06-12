package com.edu.nju.se.integration.tool;

/**
 * Created by darxan on 2017/6/10.
 */
public class StringTool {


    public static String appendSQL(int size, String field) {
        String queryString;
        queryString = " and ( "+field+"  ? ";
        for (int i=1; i<size; i++) {
            queryString += " or "+field+"  ? ";
        }
        queryString += " ) ";

        return queryString;
    }

    public static boolean validString(String str) {
        return str!=null && str.length()>0;
    }

    public static String join( String[] o , String flag ) {

        StringBuilder str_buff = new StringBuilder();
        for(int i=0 , len=o.length ; i<len ; i++){
            str_buff.append( o[i] );
            if(i<len-1)str_buff.append( flag );
        }

        return str_buff.toString();
    }

    public static double toDouble(String string, double defaultValue) {
        try {
            return Double.parseDouble(string.replaceAll("[^0-9\\.]",""));
        }catch (Exception e) {
            return defaultValue;
        }
    }


    public static void main(String[] args ){
        System.out.println(toDouble("80.0%", 1)
        );
    }
}
