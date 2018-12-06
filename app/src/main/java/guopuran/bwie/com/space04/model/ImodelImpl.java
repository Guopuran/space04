package guopuran.bwie.com.space04.model;

import guopuran.bwie.com.space04.NetUtil;
import guopuran.bwie.com.space04.callback.MyCallBack;

public class ImodelImpl implements Imodel {
    @Override
    public void modelrequest(String url, String params, final Class clazz, final MyCallBack callBack) {
        NetUtil.yibu(url, clazz, new NetUtil.CallBack() {
            @Override
            public void getdata(Object o) {
                callBack.getdata(o);
            }

//            @Override
//            public void getdata(Class t) {
//
//            }

//            @Override
//            public void getdata(Object o) {
//                callBack.getdata(o);
//            }
        });
    }
}
