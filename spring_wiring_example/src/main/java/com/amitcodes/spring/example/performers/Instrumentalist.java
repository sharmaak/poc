package com.amitcodes.spring.example.performers;

import com.amitcodes.spring.example.instrument.MusicalInstrument;

public class Instrumentalist {

    protected MusicalInstrument instrument;

    public void setInstrument(MusicalInstrument instrument) {
        this.instrument = instrument;
    }

    public MusicalInstrument getInstrument() {
        return instrument;
    }

    public String perform() {
        return instrument.play();
    }
}
