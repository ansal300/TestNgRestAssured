package com.myframework.test;

import utils.JsonUtils;

import java.io.IOException;
import java.util.Map;

public class Base {

    public static Map<String,Object> data;
   static {
       String environment=System.getProperty("Env");
       String env=environment==null?"qa":environment;
       try {
           data= JsonUtils.getJsoDataAsMap("/booking/"+env+"/bookingData.json");
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}
