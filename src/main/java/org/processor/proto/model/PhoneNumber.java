package org.processor.proto.model;

import lombok.*;


@Builder
@Getter
@AllArgsConstructor
@Setter
public class PhoneNumber {
        PhoneNumber (){ }
        String number;
        String type;
}
