package com.margo.postcard.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Margo on 13.05.2018.
 */

public class UserAccount {
    @SerializedName("nsid")
    private String userId;

    @SerializedName("iconserver")
    private String iconserver;

    @SerializedName("iconfarm")
    private Integer iconfarm;

    @SerializedName("username")
    private String userName;

    @SerializedName("realname")
    private String realName;

    @SerializedName("location")
    private String location;

    @SerializedName("description")
    private String description;

    @SerializedName("mobileurl")
    private String mobileurl;

    @SerializedName("photos")
    private PhotosCount photosCount = new PhotosCount();

    private class IntContent {

        @SerializedName("_content")
        public int content;

    }

    private class PhotosCount {

        @SerializedName("count")
        public IntContent count = new IntContent();

    }

 /*   @Override
    public String getUserProfilePicUrl() {
        return String.format(userProfileUrl,iconfarm,iconserver,userId);
    }*/

    public String getRealName() {
        if (this.getRealname()==null || this.getRealname().isEmpty()){
            return getUsername();
        }
        return getRealname();
    }

    public String getUserId() {
        return userId;
    }

    public String getIconserver() {
        return iconserver;
    }

    public Integer getIconfarm() {
        return iconfarm;
    }

    public String getUsername() {
        return userName;
    }

    public String getRealname() {
        return realName;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getMobileurl() {
        return mobileurl;
    }

    public int getPhotosCount() {
        return photosCount.count.content;
    }

    @Override
    public String toString() {
        return "FlickrUser{" +
                "userId='" + userId + '\'' +
                ", iconserver='" + iconserver + '\'' +
                ", iconfarm=" + iconfarm +
                ", username=" + userName +
                ", realname=" + realName +
                ", location=" + location +
                ", description=" + description +
                ", mobileurl=" + mobileurl +
                ", photosCount=" + photosCount.count.content +
                '}';
    }
}
