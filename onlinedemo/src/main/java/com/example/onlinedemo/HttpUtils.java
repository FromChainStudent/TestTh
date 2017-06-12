package com.example.onlinedemo;

/**
 * Created by YC on 2017/6/11.
 *
 */

public class HttpUtils {
    private static final String KEY = "?key=a56a124c89d6b6c7b62985b2131fe6bc";
    public static final String MOVIE = "http://v.juhe.cn/movie/index" + KEY + "&title=";
    public static final String NEAR_LOCAL = "http://v.juhe.cn/movie/cinemas.local" + KEY + "&dtype=json&cityid=";
    public static final String SERACH = "http://v.juhe.cn/movie/cinemas.search.php" + KEY + "&cityid=";
    public static final String MOVIE_INFO = "http://v.juhe.cn/movie/cinemas.movies" + KEY + "&cinemaid=";
    public static final String MOVIE_TODAY = "http://v.juhe.cn/movie/movies.today" + KEY + "&cityid=";
    public static final String CITYS = "http://v.juhe.cn/movie/citys" + KEY;
    public static final String MOVIE_LOCAL = "http://v.juhe.cn/movie/movies.cinemas" + KEY;
    public static final String QUERY_ID = "http://v.juhe.cn/movie/query" + KEY + "&movieid=";
}
