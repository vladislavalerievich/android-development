package com.atul.audioplayer.model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.atul.audioplayer.MPConstants;

import java.util.List;

@Entity(tableName = MPConstants.MUSIC_TABLE)
public class PlayList implements Parcelable {
    public static final Creator<PlayList> CREATOR = new Creator<PlayList>() {
        @Override
        public PlayList createFromParcel(Parcel in) {
            return new PlayList(in);
        }

        @Override
        public PlayList[] newArray(int size) {
            return new PlayList[size];
        }
    };
    @NonNull
    @PrimaryKey
    public String title = "";
    public List<Music> musics;

    public PlayList() {
    }

    protected PlayList(Parcel in) {
        title = in.readString();
        musics = in.createTypedArrayList(Music.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeTypedList(musics);
    }
}
