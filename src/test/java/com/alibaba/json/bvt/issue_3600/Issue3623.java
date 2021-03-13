package com.alibaba.json.bvt.issue_3600;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Issue3623 {
    private static final String str = "abcd";

    @Test
    public void test3() {
        Map<byte[], byte[]> map = new HashMap<byte[], byte[]>();
        map.put(str.getBytes(), str.getBytes());
        byte[] noClassName = JSON.toJSONBytes(map);
        byte[] ClassName = JSON.toJSONBytes(map, SerializerFeature.WriteClassName);
        String strNoClassName = new String(noClassName);
        String strClassName = new String(ClassName);
        System.out.println(strNoClassName);
        System.out.println(strClassName);
        Map<byte[], byte[]> map1 = JSON.parseObject(strNoClassName, new TypeReference<Map<byte[], byte[]>>(byte[].class, byte[].class) {
        });
        Map<byte[], byte[]> map2 = JSON.parseObject(strClassName, new TypeReference<Map<byte[], byte[]>>(byte[].class, byte[].class) {
        });
        Assert.assertNotNull("first",JSON.parseObject(noClassName,map.getClass()));
        Assert.assertNotNull("second",JSON.parseObject(ClassName,map.getClass()));
    }

    @Data
    public static class Pojo2 implements Serializable {
        private Object[] values;
    }
}