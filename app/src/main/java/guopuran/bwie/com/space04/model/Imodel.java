package guopuran.bwie.com.space04.model;

import guopuran.bwie.com.space04.callback.MyCallBack;

public interface Imodel {
    void modelrequest(String url, String params, Class clazz, MyCallBack callBack);
}
