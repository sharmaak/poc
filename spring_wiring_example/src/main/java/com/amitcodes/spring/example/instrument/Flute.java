package com.amitcodes.spring.example.instrument;

public class Flute implements MusicalInstrument{

    public Flute() {
    }

    @Override
    public String play() {
        return "Flute playing";
    }
}
