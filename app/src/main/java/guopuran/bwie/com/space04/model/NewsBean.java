package guopuran.bwie.com.space04.model;

import java.util.List;

public class NewsBean {
    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public class Data{
        private String thumbnail_pic_s;
        private String title;

        public String getThumbnail_pic_s() {
            return thumbnail_pic_s;
        }

        public String getTitle() {
            return title;
        }
    }
}
