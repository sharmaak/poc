package com.amitcodes.uservices.dto;

import java.util.List;

public class SearchResultDTO
{
    private int resultCount;
    private List<DocumentDTO> results;

    public SearchResultDTO(int resultCount, List<DocumentDTO> results)
    {
        this.resultCount = resultCount;
        this.results = results;
    }

    public int getResultCount()
    {
        return resultCount;
    }

    public List<DocumentDTO> getResults()
    {
        return results;
    }
}
