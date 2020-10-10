package org.processor.proto.model;

import lombok.*;

    @Builder
    @Getter
    @AllArgsConstructor
    @Setter
    public class Guest {

    Guest(){

    }
    String name;
        Integer id;
        String email;
        PhoneNumber []phoneNumbers;
    }

