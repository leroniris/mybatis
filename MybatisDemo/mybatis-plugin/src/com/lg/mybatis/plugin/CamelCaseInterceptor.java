package com.lg.mybatis.plugin;


import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Statement;
import java.util.*;

//标注要对哪一个组件的哪一个方法做拦截增强
@Intercepts(@Signature(
        args = {Statement.class},
        method = "handleResultSets",
        type=ResultSetHandler.class //对结果集做增强
))
public class CamelCaseInterceptor implements Interceptor {

    private Properties properties;

    //如何做增强的细节
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        List<Object> list = (List<Object>) invocation.proceed();
        for (Object obj: list) {
            if(!(obj instanceof Map)) {
                break;
            }
            //如果每一行数据的封装对象是map实例
            handleMap((Map<String, Object>) obj);
        }
        return list;
    }

    private void handleMap(Map<String, Object> map) {
        Set<String> keySet = new HashSet<>(map.keySet());
        for(String key : keySet) {
            //判断key是否是大写字母开头或带有下划线
            if(key.contains("_")){
                Object value = map.get(key);
                System.out.println(key + "----" + value);
                map.remove(key);
                String newKey = handleKey(key);
                map.put(newKey, value);
            }
        }
    }

    private String handleKey(String key) {
        StringBuilder sb = new StringBuilder(10);
        boolean findUnderLine = false; //是否找到下划线
        for(int index = 0; index < key.length(); index++) {
            char ch = key.charAt(index);//每一个字母
            if(ch == '_') {
                findUnderLine = true;
            } else {
                if(findUnderLine) {
                    sb.append(Character.toUpperCase(ch));
                    findUnderLine = false;
                } else {
                    sb.append(Character.toLowerCase(ch));
                }
            }
        }
        return sb.toString();
    }

    //包装
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    //给拦截器设置一些配置参数，配置可以由使用拦截器的人来给定
    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
