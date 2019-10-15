package com.lg.mybatis.param.proxy;

import lombok.Setter;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Setter
public class MyMapperProxy<T> implements InvocationHandler {

    private Class<T> mapperInterface;
    private SqlSession session;

    public T getProxyObject() {
        return (T) Proxy.newProxyInstance(
                mapperInterface.getClassLoader(),
                new Class[] {mapperInterface},
                this);
    }

    //具体怎么增强
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //Mapper接口的全限定名称
        String namespaceName = mapperInterface.getName();
        //方法名称
        String methodName = method.getName();
        String statement = namespaceName + "." + methodName;
        return session.selectList(statement);
    }
}
