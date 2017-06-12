package com.example.onlinedemo.bean;

import java.util.List;

/**
 * Created by YC on 2017/6/11.
 */

public class HostMovie {

    /**
     * reason : success
     * result : [{"movieId":"21250","movieName":"白日梦想家","pic_url":"http://img31.mtime.cn/mt/2013/11/26/074429.68199128_96X128.jpg"},{"movieId":"134509","movieName":"最强囍事","pic_url":"http://img21.mtime.cn/mt/2011/01/13/122711.93922385_96X128.jpg"},{"movieId":"143038","movieName":"天才眼镜狗","pic_url":"http://img31.mtime.cn/mt/2014/03/06/090305.77794647_96X128.jpg"},{"movieId":"151951","movieName":"美国队长2","pic_url":"http://img31.mtime.cn/mt/2014/03/16/154554.36400206_96X128.jpg"},{"movieId":"172743","movieName":"魔警","pic_url":"http://img31.mtime.cn/mt/2014/04/09/085413.72005937_96X128.jpg"},{"movieId":"177879","movieName":"里约大冒险2","pic_url":"http://img31.mtime.cn/mt/2014/03/20/091804.71943568_96X128.jpg"},{"movieId":"178498","movieName":"整容日记","pic_url":"http://img31.mtime.cn/mt/2014/04/04/185750.94280401_96X128.jpg"},{"movieId":"180842","movieName":"201314","pic_url":"http://img31.mtime.cn/mt/2012/12/20/134302.99793240_96X128.jpg"},{"movieId":"181203","movieName":"盟军夺宝队","pic_url":"http://img31.mtime.cn/mt/2014/03/06/112340.71755460_96X128.jpg"},{"movieId":"190465","movieName":"超验骇客","pic_url":"http://img31.mtime.cn/mt/2014/03/07/165346.19559684_96X128.jpg"},{"movieId":"190494","movieName":"再爱一次好不好","pic_url":"http://img31.mtime.cn/mt/2014/03/24/161434.19041972_96X128.jpg"},{"movieId":"195986","movieName":"狂舞派","pic_url":"http://img31.mtime.cn/mt/2013/08/08/172715.27242932_96X128.jpg"},{"movieId":"200310","movieName":"大力神","pic_url":"http://img31.mtime.cn/mt/2014/04/02/121553.40570610_96X128.jpg"},{"movieId":"203734","movieName":"追爱大布局","pic_url":"http://img31.mtime.cn/mt/2014/04/08/112633.32015390_96X128.jpg"},{"movieId":"205228","movieName":"百变爱人","pic_url":"http://img31.mtime.cn/mt/2014/03/18/142633.24585044_96X128.jpg"},{"movieId":"207415","movieName":"特工艾米拉","pic_url":"http://img31.mtime.cn/mt/2014/04/01/112811.60380935_96X128.jpg"},{"movieId":"209201","movieName":"硬汉奶爸","pic_url":"http://img31.mtime.cn/mt/2014/04/08/105211.53977334_96X128.jpg"},{"movieId":"209208","movieName":"笔仙惊魂3","pic_url":"http://img31.mtime.cn/mt/2014/04/01/111740.74687087_96X128.jpg"},{"movieId":"209220","movieName":"最佳嫌疑人","pic_url":"http://img31.mtime.cn/mt/2014/04/14/142239.57219598_96X128.jpg"},{"movieId":"210066","movieName":"爱你一世一生","pic_url":"http://img31.mtime.cn/mt/2014/04/10/121437.97963221_96X128.jpg"}]
     * error_code : 0
     */

    private String reason;
    private int error_code;
    private List<ResultBean> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * movieId : 21250
         * movieName : 白日梦想家
         * pic_url : http://img31.mtime.cn/mt/2013/11/26/074429.68199128_96X128.jpg
         */

        private String movieId;
        private String movieName;
        private String pic_url;

        public String getMovieId() {
            return movieId;
        }

        public void setMovieId(String movieId) {
            this.movieId = movieId;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }
    }
}
