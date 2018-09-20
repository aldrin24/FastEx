package com.jtanveer.fastex.model.delivery;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

@Entity
public class Delivery implements Parcelable {

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @Embedded
    @SerializedName("location")
    @Expose
    private Location location;

    public Delivery(@NonNull Long id, String description, String imageUrl, Location location) {
        this.id = id;
        this.description = description;
        this.imageUrl = imageUrl;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public static final DiffUtil.ItemCallback<Delivery> DIFF_CALLBACK = new DiffUtil.ItemCallback<Delivery>() {
        @Override
        public boolean areItemsTheSame(@NonNull Delivery oldDelivery, @NonNull Delivery newDelivery) {
            return oldDelivery.id == newDelivery.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Delivery oldDelivery, @NonNull Delivery newDelivery) {
            return oldDelivery.description.equals(newDelivery.description);
        }
    };

    protected Delivery(Parcel in) {
        id = Objects.requireNonNull(in.readByte() == 0x00 ? null : in.readLong());
        description = in.readString();
        imageUrl = in.readString();
        location = (Location) in.readValue(Location.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (0x01));
        dest.writeLong(id);
        dest.writeString(description);
        dest.writeString(imageUrl);
        dest.writeValue(location);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Delivery> CREATOR = new Parcelable.Creator<Delivery>() {
        @Override
        public Delivery createFromParcel(Parcel in) {
            return new Delivery(in);
        }

        @Override
        public Delivery[] newArray(int size) {
            return new Delivery[size];
        }
    };

}