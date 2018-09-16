package com.zhyl.bean;

public class TrackFence {
    private String id;
    private Pos[] fenceTrack;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Pos[] getFenceTrack() {
        return fenceTrack;
    }

    public void setFenceTrack(Pos[] fenceTrack) {
        this.fenceTrack = fenceTrack;
    }
}
