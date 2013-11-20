package com.amitcodes.spring.example.instrument;

public class Drum implements MusicalInstrument{

    public Drum() {
    }

    @Override
    public String play() {
        return "Drum playing";
    }
}
