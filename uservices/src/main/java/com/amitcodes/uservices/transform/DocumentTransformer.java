package com.amitcodes.uservices.transform;

import com.amitcodes.uservices.dto.DocumentDTO;
import com.amitcodes.uservices.model.Document;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DocumentTransformer
{
    private static final DateTimeFormatter TIMESTAMP_FORMATTER =
            DateTimeFormat.forPattern("dd-MMMM-YYYY, HH:mm:ss a Z");


    public List<DocumentDTO> toDocumentDTO(Collection<Document> documents){
        if(documents == null || documents.isEmpty()){
            return new ArrayList<>(0);
        }

        List<DocumentDTO> dtos = new ArrayList<>(documents.size());
        for(Document document : documents) {
            dtos.add(toDocumentDTO(document));
        }

        return dtos;
    }

    public DocumentDTO toDocumentDTO(Document document){
        DocumentDTO dto = new DocumentDTO();

        dto.setId(document.getId());
        dto.setName(document.getName());
        dto.setPath(document.getPath());

        DateTime ct = document.getCreationTime();
        String ts = ct==null ? null : ct.toString(TIMESTAMP_FORMATTER);
        dto.setCreationTime(ts);

        return dto;
    }

    public List<Document> toDocument(Collection<DocumentDTO> dtos){
        if(dtos == null || dtos.isEmpty()){
            return new ArrayList<>(0);
        }

        List<Document> docs = new ArrayList<>(dtos.size());
        for(DocumentDTO dto : dtos){
            docs.add(toDocument(dto));
        }

        return docs;
    }

    public Document toDocument(DocumentDTO dto){
        Document doc = new Document();
        doc.setId(dto.getId());
        doc.setName(dto.getName());
        doc.setPath(dto.getPath());

        String ts = dto.getCreationTime();
        DateTime crt  = ( ts == null ? null : DateTime.parse(ts, TIMESTAMP_FORMATTER) );
        doc.setCreationTime(crt);

        return doc;
    }
}
