package com.atul.audioplayer.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.atul.audioplayer.helper.ListHelper;

import org.jetbrains.annotations.NotNull;

public class Music implements Parcelable {

    public static final Creator<Music> CREATOR = new Creator<Music>() {
        @Override
        public Music createFromParcel(Parcel in) {
            return new Music(in);
        }

        @Override
        public Music[] newArray(int size) {
            return new Music[size];
        }
    };
    public String artist;
    public String title;
    public String displayName;
    public String album;
    public String relativePath;
    public String absolutePath;
    public String albumArt;
    public int year;
    public int track;
    public int startFrom;
    public long id;
    public long duration;
    public long albumId;

    public Music(String artist, String title, String displayName, String album, String relativePath, String absolutePath,
                 int year, int track, int startFrom,
                 long id, long duration, long albumId,
                 Uri albumArt) {
        this.artist = ListHelper.ifNull(artist);
        this.title = ListHelper.ifNull(title);
        this.displayName = ListHelper.ifNull(displayName);
        this.album = ListHelper.ifNull(album);
        this.relativePath = ListHelper.ifNull(relativePath);
        this.absolutePath = ListHelper.ifNull(absolutePath);
        this.year = year;
        this.track = track;
        this.startFrom = startFrom;
        this.id = id;
        this.duration = duration;
        this.albumId = albumId;
        this.albumArt = albumArt.toString();
    }

    protected Music(Parcel in) {
        artist = in.readString();
        title = in.readString();
        displayName = in.readString();
        album = in.readString();
        relativePath = in.readString();
        absolutePath = in.readString();
        albumArt = in.readString();
        year = in.readInt();
        track = in.readInt();
        startFrom = in.readInt();
        id = in.readLong();
        duration = in.readLong();
        albumId = in.readLong();
    }

    @NotNull
    @Override
    public String toString() {
        return "Music{" +
                "artist='" + artist + '\'' +
                ", title='" + title + '\'' +
                ", displayName='" + displayName + '\'' +
                ", album='" + album + '\'' +
                ", relativePath='" + relativePath + '\'' +
                ", absolutePath='" + absolutePath + '\'' +
                ", year=" + year +
                ", track=" + track +
                ", startFrom=" + startFrom +
                ", id=" + id +
                ", duration=" + duration +
                ", albumId=" + albumId +
                ", albumArt=" + albumArt +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(artist);
        dest.writeString(title);
        dest.writeString(displayName);
        dest.writeString(album);
        dest.writeString(relativePath);
        dest.writeString(absolutePath);
        dest.writeString(albumArt);
        dest.writeInt(year);
        dest.writeInt(track);
        dest.writeInt(startFrom);
        dest.writeLong(id);
        dest.writeLong(duration);
        dest.writeLong(albumId);
    }
}
