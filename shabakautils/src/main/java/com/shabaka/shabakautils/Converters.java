package com.shabaka.shabakautils;

import android.content.Context;
import android.graphics.Color;

import androidx.core.content.ContextCompat;

import com.google.common.base.CaseFormat;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Converters {

    /**
     * convert color resource ID to real hex color
     * @param context application context
     * @param colorId color ID
     * @return
     */
    public static int ColorConverter(Context context, int colorId){
        return Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context,colorId)));
    }

    /**
     * Color Switcher for background color, to switch between two state
     * @param context
     * @param switchCondition
     * @param colorResourceId
     * @param secondColorResourceId
     * @return
     */

    public static int SwitchBackColorConverter(Context context, boolean switchCondition, int colorResourceId, int secondColorResourceId){
        if(switchCondition)
            return Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context, colorResourceId)));
        return Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context, secondColorResourceId)));
    }
    /**
     * Color Switcher for foreground color, to switch between two state
     * @param context
     * @param switchCondition
     * @param colorResourceId
     * @param secondColorResourceId
     * @return
     */
    public static int SwitchForColorConverter(Context context, boolean switchCondition, int colorResourceId, int secondColorResourceId){
        if(switchCondition)
            return Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context, colorResourceId)));
        return Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context, secondColorResourceId)));
    }

    /**
     * Convert to local price
     * @param value
     * @return
     */
    public static String localPriceConverter(double value){
        return (int)value + " DA";
    }

    /**
     * Get days with label
     * @param value
     * @return
     */
    public static String dayNumbersConverter(int value){
        return value + " Jours";
    }

    /**
     * percent double to string
     * @param value
     * @return
     */
    public static String percentConverter(double value){

        return (int)value + " %";
    }

    /**
     * qte with given unite
     * @param value
     * @param unite
     * @return
     */
    public static String qteUniteConverter(double value, String unite){
        if(unite == null || unite.isEmpty()) return (int)value + " Unite(s)";
        return (int)value + " " + unite;
    }

    /**
     * convert django DRF url to Entity ID
     * @param url
     * @return
     */
    public static String djangoUrlToIdConverter(String url){
        String[] urlParts = url.split("/");
        if(urlParts.length == 0) return "";
        return urlParts[urlParts.length - 1];
    }

    /**
     * Map Converter
     * @param object
     * @return
     * @throws
     */
    public static Map<String, String> objectMapConverter(Object object) throws IllegalAccessException {
        Map<String, String> objectMap = new HashMap<String,String>();

        Field[] allFields = object.getClass().getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            Object value = field.get(object);
            String key = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName());
            objectMap.put(key, value.toString());
        }
        return objectMap;
    }
}