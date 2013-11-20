package com.amitcodes.spring.example.performers;

public class Vocalist {

    private String lyrics;

    public Vocalist() {
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String sing() {
        return "Vocalist: " + lyrics;
    }
}
