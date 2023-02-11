package com.son.deprem;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class DepremItem implements Parcelable {

    double mag, lng, lat;
    String lokasyon;
    double depth;
    String title;
    long timestamp;

    public DepremItem(double mag, double lng, double lat, String lokasyon, double depth, String title, long timestamp) {
        this.mag = mag;
        this.lng = lng;
        this.lat = lat;
        this.lokasyon = lokasyon;
        this.depth = depth;
        this.title = title;
        this.timestamp = timestamp;
    }

    protected DepremItem(Parcel in) {
        mag = in.readDouble();
        lng = in.readDouble();
        lat = in.readDouble();
        lokasyon = in.readString();
        depth = in.readDouble();
        title = in.readString();
        timestamp = in.readLong();
    }

    public static final Creator<DepremItem> CREATOR = new Creator<DepremItem>() {
        @Override
        public DepremItem createFromParcel(Parcel in) {
            return new DepremItem(in);
        }

        @Override
        public DepremItem[] newArray(int size) {
            return new DepremItem[size];
        }
    };

    public double getMag() {
        return mag;
    }

    public void setMag(double mag) {
        this.mag = mag;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getLokasyon() {
        return lokasyon;
    }

    public void setLokasyon(String lokasyon) {
        this.lokasyon = lokasyon;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeDouble(mag);
        parcel.writeDouble(lng);
        parcel.writeDouble(lat);
        parcel.writeString(lokasyon);
        parcel.writeDouble(depth);
        parcel.writeString(title);
        parcel.writeLong(timestamp);
    }
}
