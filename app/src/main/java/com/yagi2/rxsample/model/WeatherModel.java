package com.yagi2.rxsample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class WeatherModel implements Serializable {
    @Expose
    @SerializedName("location")
    public Location location;

    @Expose
    @SerializedName("title")
    public String title;

    @Expose
    @SerializedName("link")
    public String link;

    @Expose
    @SerializedName("publicTime")
    public String publicTime;

    @Expose
    @SerializedName("description")
    public Description description;

    @Expose
    @SerializedName("forecasts")
    public List<Forecasts> forecasts;

    @Expose
    @SerializedName("pinpointLocation")
    public PinpointLocation pinpointLocation;

    @Expose
    @SerializedName("copyright")
    public Copyright copyright;

    public class Location implements Serializable {
        @Expose
        @SerializedName("city")
        public String city;

        @Expose
        @SerializedName("area")
        public String area;

        @Expose
        @SerializedName("prefecture")
        public String prefecture;
    }

    public class Description implements Serializable {
        @Expose
        @SerializedName("text")
        public String text;

        @Expose
        @SerializedName("publicTime")
        public String publicTime;
    }

    public class Forecasts implements Serializable {
        @Expose
        @SerializedName("date")
        public String date;

        @Expose
        @SerializedName("dateLabel")
        public String dateLabel;

        @Expose
        @SerializedName("telop")
        public String telop;

        @Expose
        @SerializedName("image")
        public Image image;

        @Expose
        @SerializedName("temperature")
        public Temperature temperature;

        public class Temperature implements Serializable {
            @Expose
            @SerializedName("min")
            public TemperatureBase min;

            @Expose
            @SerializedName("max")
            public TemperatureBase max;

            public class TemperatureBase implements Serializable {
                @Expose
                @SerializedName("celsius")
                public String celsius;

                @Expose
                @SerializedName("fahrenheit")
                public String fahrenheit;
            }
        }

        public class Image implements Serializable {
            @Expose
            @SerializedName("title")
            public String title;

            @Expose
            @SerializedName("url")
            public String url;

            @Expose
            @SerializedName("width")
            public int width;

            @Expose
            @SerializedName("height")
            public int height;
        }
    }

    public class PinpointLocation implements Serializable {
        @Expose
        @SerializedName("name")
        public String name;

        @Expose
        @SerializedName("link")
        public String link;
    }

    public class Copyright implements Serializable {
        @Expose
        @SerializedName("title")
        public String title;

        @Expose
        @SerializedName("link")
        public String link;

        @Expose
        @SerializedName("image")
        public Image image;

        @Expose
        @SerializedName("provider")
        public List<Provider> provider;

        public class Provider implements Serializable {
            @Expose
            @SerializedName("link")
            public String link;

            @Expose
            @SerializedName("name")
            public String name;
        }

        public class Image implements Serializable {
            @Expose
            @SerializedName("title")
            public String title;

            @Expose
            @SerializedName("link")
            public String link;

            @Expose
            @SerializedName("url")
            public String url;

            @Expose
            @SerializedName("width")
            public int width;

            @Expose
            @SerializedName("height")
            public int height;
        }
    }
}
