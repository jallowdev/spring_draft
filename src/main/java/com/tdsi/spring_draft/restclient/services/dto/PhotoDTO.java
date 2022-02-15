package com.tdsi.spring_draft.restclient.services.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class PhotoDTO implements Serializable {
    private long id;
    private String title;
    private String url;
    private int albumId;
    private String thumbnailUrl;


    /*PhotoDTO fromJson(Map json){
        return new PhotoDTO(json["id"],json["id"],json["id"])
    }*/
}
