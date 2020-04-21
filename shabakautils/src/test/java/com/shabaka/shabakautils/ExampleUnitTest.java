package com.shabaka.shabakautils;

import com.google.common.base.CaseFormat;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.shabaka.shabakautils.Converters.objectMapConverter;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


public class ExampleUnitTest {

    public class SomeObject{
        int id; String name; Boolean isObject; double number;

        public SomeObject(int id, String name, Boolean isObject, double number) {
            this.id = id;
            this.name = name;
            this.isObject = isObject;
            this.number = number;
        }
    }
    @Test
    public void addition_isCorrect() {
        SomeObject o = new SomeObject(1, "name", true, 15d);

        assertEquals(4, 4);
    }
    @Test
    public void map_isCorrect() {
        SomeObject o = new SomeObject(1, "name", true, 15d);

        Map<String, String> mo = new HashMap<>();
        mo.put("id", "1");
        mo.put("name", "name");
        mo.put("is_object", "true");
        mo.put("number", "15.0");
        try {
            Map<String, String> convet = objectMapConverter(o);
            for (String name: convet.keySet()){
                String key = name;
                String value = convet.get(name);
                System.out.println(key + " " + value);
            }
            assertEquals(objectMapConverter(o), mo);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}