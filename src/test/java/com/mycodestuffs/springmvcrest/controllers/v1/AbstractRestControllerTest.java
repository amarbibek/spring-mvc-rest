package com.mycodestuffs.springmvcrest.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractRestControllerTest {
    public static String asJsonString(final Object o){
        try {
            return new ObjectMapper().writeValueAsString(o);
        }catch (Exception exception){
            throw new RuntimeException();
        }
    }
}
