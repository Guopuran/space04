package guopuran.bwie.com.space04.presenter;

import java.util.regex.Pattern;

import guopuran.bwie.com.space04.callback.MyCallBack;
import guopuran.bwie.com.space04.model.ImodelImpl;
import guopuran.bwie.com.space04.view.IView;

public class IpresenterImpl implements Ipresenter {
    private ImodelImpl mImodelImpl;
    private IView mIView;

    public IpresenterImpl(IView mIView) {
        this.mIView = mIView;
        mImodelImpl=new ImodelImpl();
    }

    public void deatch(){
        if (mImodelImpl!=null){
            mImodelImpl=null;
        }
        if (mIView!=null){
            mIView=null;
        }
    }
    @Override
    public void startrequest(String url, String params, Class clazz) {
        /*if (!panduan(params)){

        }*/
        mImodelImpl.modelrequest(url, params, clazz, new MyCallBack() {
            @Override
            public void getdata(Object o) {
                mIView.getdata(o);
            }
        });
    }
    /*public boolean panduan(String name){
        final String NAME="^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$";
        return Pattern.matches(NAME,name);
    }*/
}
