package com.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Menu {

    private int id;
    private String name;
    private String page;
    private Integer parentid;
    private Integer show_in_menu;
    private String iconCSS;
    private Integer value;
    private Integer display;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_datetime;
    private String text;
    private String iconCls;
    private String state;
    private List<Menu> children;
    public Attributes attributes;
}

@Getter
@Setter
class Attributes
{
    public String url;
    public String iframe = "1";
}

