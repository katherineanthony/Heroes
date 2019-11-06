package com.example.heroes;

import android.os.Parcel;
import android.os.Parcelable;

public class Hero implements Parcelable {
    private String name;
    private String description;
    private String superpower;
    private int ranking;
    private String image;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.superpower);
        dest.writeInt(this.ranking);
        dest.writeString(this.image);
    }

    public Hero() {
    }

    protected Hero(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
        this.superpower = in.readString();
        this.ranking = in.readInt();
        this.image = in.readString();
    }

    public static final Creator<Hero> CREATOR = new Creator<Hero>() {
        @Override
        public Hero createFromParcel(Parcel source) {
            return new Hero(source);
        }

        @Override
        public Hero[] newArray(int size) {
            return new Hero[size];
        }
    };
}
