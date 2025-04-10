package com.krishna.translation_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Translate {

    public ArrayList<String> text;
    public String from;
    public String to;
}
