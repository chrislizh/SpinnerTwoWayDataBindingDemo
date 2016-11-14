package au.com.chrisli.spinnertwowaydatabindingdemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cli on 14/11/2016.
 * All Rights Reserved.
 */

public class Planet implements Parcelable {

    private String name_;
    private float distance_; //distance to Sun in AU(Astronomical Unit)

    public Planet(String name, float distance) {
        name_ = name;
        distance_ = distance;
    }

    protected Planet(Parcel in) {
        name_ = in.readString();
        distance_ = in.readFloat();
    }

    public static final Creator<Planet> CREATOR = new Creator<Planet>() {
        @Override
        public Planet createFromParcel(Parcel in) {
            return new Planet(in);
        }

        @Override
        public Planet[] newArray(int size) {
            return new Planet[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name_);
        dest.writeFloat(distance_);
    }

    @Override
    public String toString() {
        return name_ != null ? name_ : super.toString();
    }

    public String getName() {
        return name_;
    }

    public void setName(String name) {
        name_ = name;
    }

    public float getDistance() {
        return distance_;
    }

    public void setDistance(float distance) {
        distance_ = distance;
    }
}
