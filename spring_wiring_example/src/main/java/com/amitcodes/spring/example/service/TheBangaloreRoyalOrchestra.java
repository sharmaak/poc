package com.amitcodes.spring.example.service;

import com.amitcodes.spring.example.performers.Instrumentalist;
import com.amitcodes.spring.example.performers.Vocalist;

import java.util.List;

public class TheBangaloreRoyalOrchestra implements Orchestra {

    private List<Instrumentalist> instrumentalists;
    private Vocalist vocalist;

    /**********************************************************
     *                  Getters and Setters
     **********************************************************/
    public TheBangaloreRoyalOrchestra() {
    }

    public List<Instrumentalist> getInstrumentalists() {
        return instrumentalists;
    }

    public void setInstrumentalists(List<Instrumentalist> instrumentalists) {
        this.instrumentalists = instrumentalists;
    }

    public Vocalist getVocalist() {
        return vocalist;
    }

    public void setVocalist(Vocalist vocalist) {
        this.vocalist = vocalist;
    }

    /**********************************************************
     *                  Public Methods
     **********************************************************/
    @Override
    public String perform() {

        StringBuilder music = new StringBuilder();
        for (Instrumentalist anInstrumentalists : instrumentalists) {
            music.append(anInstrumentalists.perform()).append('\n');
        }
        music.append(vocalist.sing());
        return music.toString();
    }

}
