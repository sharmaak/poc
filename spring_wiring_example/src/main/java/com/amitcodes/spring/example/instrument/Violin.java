package com.amitcodes.spring.example.instrument;

public class Violin implements MusicalInstrument{

    public Violin() {
    }

    @Override
    public String play() {
        return "Violin playing";
    }
}
