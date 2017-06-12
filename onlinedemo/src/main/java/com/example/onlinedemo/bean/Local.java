package com.example.onlinedemo.bean;

import java.util.List;

/**
 * Created by YC on 2017/6/11.
 */

public class Local {

    /**
     * reason : success
     * result : {"data":[{"id":"141","cityName":"北京","cinemaName":"北京博纳影城朝阳门旗舰店","address":"北京市朝阳区三丰北里2号楼悠唐生活广场B1层（朝阳门钱柜对面）","telephone":"010-59775660","latitude":"39.92223","longitude":"116.4388","trafficRoutes":"乘坐101电，109电，110，112电，420，750路公交在朝阳门外站下车，悠唐生活广场地下一层"},{"id":"142","cityName":"北京","cinemaName":"北京橙天嘉禾吉彩影城","address":"北京海淀区玉海园五里22号配套商业楼（原吉彩体育馆）","telephone":"","latitude":"39.92282","longitude":"116.2596","trafficRoutes":"地铁一号线 玉泉路下车，B2出口出，乘坐507、78、981、612、运通114 玉海园东门下车即到。"},{"id":"143","cityName":"北京","cinemaName":"首都电影院（金融街店）","address":"金融大街18号金融街购物中心二期地下一层","telephone":"010-66086662","latitude":"39.91509","longitude":"116.3604","trafficRoutes":"7路、21路、38路、44路、50路、46路、68路、84路、387路、423路、456路、477路、618路、691路、694路、939路复兴门南站\n"},{"id":"144","cityName":"北京","cinemaName":"博纳国际影城通州店","address":"北京市通州区杨庄北里天时名苑14号楼F4-01","telephone":"010-56351916-800","latitude":"39.90354","longitude":"116.6367","trafficRoutes":"地铁：城铁八通线通州北苑站B出口。\n公交：312、322、649、666、667、668、669、728、801、804、805、810、924、811、938、916、976、916、924、930、372、435、615、647、郊87、通12路到北苑站下车即可。"},{"id":"145","cityName":"北京","cinemaName":"大地数字影院-北京望京新天地","address":"北京市朝阳区望京合生麒麟社影院","telephone":"010-57389754","latitude":"39.99717","longitude":"116.4774","trafficRoutes":"420路、467路、470路、471路、538路、621路、运通104线至望京西园四区下车即可"},{"id":"146","cityName":"北京","cinemaName":"大地数字影院-北京延庆金锣湾","address":"北京市延庆县妫水北街39号1幢H座首层（金锣湾商业中心）沃尔玛旁","telephone":"010-60165114","latitude":"40.46144","longitude":"115.9765","trafficRoutes":"920、919、921、925路公车到达"},{"id":"147","cityName":"北京","cinemaName":"大地数字影院-通州马驹桥米拉","address":"北京市通州区新油东路1号米拉时尚生活广场七层","telephone":"010-80897926","latitude":"39.76124","longitude":"116.5453","trafficRoutes":"凉水河桥南-公交车站：542路环线, 723路, 826路, 846路, 975路,976路, 专41路\n马驹桥新桥-公交车站：28-29联运, 821路, 846路, 927路, 976路,郊87路, 郊87路空调, 通12路, 通28路"},{"id":"148","cityName":"北京","cinemaName":"大地数字影城--西三旗物美","address":"北京海淀区悦秀路99号二层大地影院","telephone":"010-60603728","latitude":"40.05296","longitude":"116.3376","trafficRoutes":"2路、753路、478路、315路、614路"},{"id":"149","cityName":"北京","cinemaName":"UME国际影城(安贞店)","address":"北京市东城区北三环东路36号环球贸易中心E座B1/F1/F3","telephone":"010-58257733","latitude":"39.96822","longitude":"116.4103","trafficRoutes":"乘公交车：安贞桥东下车：300、683、117、718、302、998、731、954、985、特8、运通104和平西桥南下车：125、430、75\n乘坐地铁5号线可在和平西桥站下车，从A或C出口出站向西行\n"},{"id":"150","cityName":"北京","cinemaName":"首都电影院（西单店）","address":"西城区西单北大街甲131号大悦城商场十层","telephone":"010-66086662","latitude":"39.91076","longitude":"116.3733","trafficRoutes":"可乘1、10、22、37、52、70、728在西单路口东下车。可乘102、105、109、22、46、47、603、604、626、690、在西单商场下车。也可乘地铁1号线在西单下车走西北出口。自驾前往，有地下停车场。"}],"page":1,"pagesize":10,"totalpage":14}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * data : [{"id":"141","cityName":"北京","cinemaName":"北京博纳影城朝阳门旗舰店","address":"北京市朝阳区三丰北里2号楼悠唐生活广场B1层（朝阳门钱柜对面）","telephone":"010-59775660","latitude":"39.92223","longitude":"116.4388","trafficRoutes":"乘坐101电，109电，110，112电，420，750路公交在朝阳门外站下车，悠唐生活广场地下一层"},{"id":"142","cityName":"北京","cinemaName":"北京橙天嘉禾吉彩影城","address":"北京海淀区玉海园五里22号配套商业楼（原吉彩体育馆）","telephone":"","latitude":"39.92282","longitude":"116.2596","trafficRoutes":"地铁一号线 玉泉路下车，B2出口出，乘坐507、78、981、612、运通114 玉海园东门下车即到。"},{"id":"143","cityName":"北京","cinemaName":"首都电影院（金融街店）","address":"金融大街18号金融街购物中心二期地下一层","telephone":"010-66086662","latitude":"39.91509","longitude":"116.3604","trafficRoutes":"7路、21路、38路、44路、50路、46路、68路、84路、387路、423路、456路、477路、618路、691路、694路、939路复兴门南站\n"},{"id":"144","cityName":"北京","cinemaName":"博纳国际影城通州店","address":"北京市通州区杨庄北里天时名苑14号楼F4-01","telephone":"010-56351916-800","latitude":"39.90354","longitude":"116.6367","trafficRoutes":"地铁：城铁八通线通州北苑站B出口。\n公交：312、322、649、666、667、668、669、728、801、804、805、810、924、811、938、916、976、916、924、930、372、435、615、647、郊87、通12路到北苑站下车即可。"},{"id":"145","cityName":"北京","cinemaName":"大地数字影院-北京望京新天地","address":"北京市朝阳区望京合生麒麟社影院","telephone":"010-57389754","latitude":"39.99717","longitude":"116.4774","trafficRoutes":"420路、467路、470路、471路、538路、621路、运通104线至望京西园四区下车即可"},{"id":"146","cityName":"北京","cinemaName":"大地数字影院-北京延庆金锣湾","address":"北京市延庆县妫水北街39号1幢H座首层（金锣湾商业中心）沃尔玛旁","telephone":"010-60165114","latitude":"40.46144","longitude":"115.9765","trafficRoutes":"920、919、921、925路公车到达"},{"id":"147","cityName":"北京","cinemaName":"大地数字影院-通州马驹桥米拉","address":"北京市通州区新油东路1号米拉时尚生活广场七层","telephone":"010-80897926","latitude":"39.76124","longitude":"116.5453","trafficRoutes":"凉水河桥南-公交车站：542路环线, 723路, 826路, 846路, 975路,976路, 专41路\n马驹桥新桥-公交车站：28-29联运, 821路, 846路, 927路, 976路,郊87路, 郊87路空调, 通12路, 通28路"},{"id":"148","cityName":"北京","cinemaName":"大地数字影城--西三旗物美","address":"北京海淀区悦秀路99号二层大地影院","telephone":"010-60603728","latitude":"40.05296","longitude":"116.3376","trafficRoutes":"2路、753路、478路、315路、614路"},{"id":"149","cityName":"北京","cinemaName":"UME国际影城(安贞店)","address":"北京市东城区北三环东路36号环球贸易中心E座B1/F1/F3","telephone":"010-58257733","latitude":"39.96822","longitude":"116.4103","trafficRoutes":"乘公交车：安贞桥东下车：300、683、117、718、302、998、731、954、985、特8、运通104和平西桥南下车：125、430、75\n乘坐地铁5号线可在和平西桥站下车，从A或C出口出站向西行\n"},{"id":"150","cityName":"北京","cinemaName":"首都电影院（西单店）","address":"西城区西单北大街甲131号大悦城商场十层","telephone":"010-66086662","latitude":"39.91076","longitude":"116.3733","trafficRoutes":"可乘1、10、22、37、52、70、728在西单路口东下车。可乘102、105、109、22、46、47、603、604、626、690、在西单商场下车。也可乘地铁1号线在西单下车走西北出口。自驾前往，有地下停车场。"}]
         * page : 1
         * pagesize : 10
         * totalpage : 14
         */

        private int page;
        private int pagesize;
        private int totalpage;
        private List<DataBean> data;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPagesize() {
            return pagesize;
        }

        public void setPagesize(int pagesize) {
            this.pagesize = pagesize;
        }

        public int getTotalpage() {
            return totalpage;
        }

        public void setTotalpage(int totalpage) {
            this.totalpage = totalpage;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 141
             * cityName : 北京
             * cinemaName : 北京博纳影城朝阳门旗舰店
             * address : 北京市朝阳区三丰北里2号楼悠唐生活广场B1层（朝阳门钱柜对面）
             * telephone : 010-59775660
             * latitude : 39.92223
             * longitude : 116.4388
             * trafficRoutes : 乘坐101电，109电，110，112电，420，750路公交在朝阳门外站下车，悠唐生活广场地下一层
             */

            private String id;
            private String cityName;
            private String cinemaName;
            private String address;
            private String telephone;
            private String latitude;
            private String longitude;
            private String trafficRoutes;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public String getCinemaName() {
                return cinemaName;
            }

            public void setCinemaName(String cinemaName) {
                this.cinemaName = cinemaName;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getTrafficRoutes() {
                return trafficRoutes;
            }

            public void setTrafficRoutes(String trafficRoutes) {
                this.trafficRoutes = trafficRoutes;
            }
        }
    }
}
