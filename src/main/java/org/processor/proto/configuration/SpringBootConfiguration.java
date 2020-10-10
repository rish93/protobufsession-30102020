package org.processor.proto.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.processor.proto.model.GuestProtos;
import org.processor.proto.repository.GuestRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Configuration
public class SpringBootConfiguration {

    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }
        /*
        Without HttpMessageConvertor we may get exception as:
            "exception": "org.springframework.http.converter.HttpMessageNotWritableException",
            "message": "Could not write content: Direct self-reference leading to cycle (through reference chain: org.processor.proto.model.Guest[\"unknownFields\"]->com.google.protobuf.UnknownFieldSet[\"defaultInstanceForType\"]); nested exception is com.fasterxml.jackson.databind.JsonMappingException: Direct self-reference leading to cycle (through reference chain: org.processor.proto.model.Guest[\"unknownFields\"]->com.google.protobuf.UnknownFieldSet[\"defaultInstanceForType\"])",
        */


    @Bean
    GuestRepository guestRepository() {
        final Map<Integer, GuestProtos.Guest> guest = new ConcurrentHashMap<>();
        // populate with some dummy data
        Arrays.asList(
                guest(1, "Chris", "richardson.chris@gmail.com", Arrays.asList("+91 123244353","+49 8847777321" )),
                guest(2, "Josh", "long.josh@hotmail.om", Arrays.asList("+91 123244353","+91 983342239","+91 999944353")),
                guest(3, "Matt", "tine.matt@msn.com", Arrays.asList("+47 3453323490")),
                guest(4, "Russ", "miles.ruess@yahoo.com", Arrays.asList("0522 268828"))
        ).forEach(c -> guest.put(c.getId(), c));

        return o -> guest.get(o);
    }

    private GuestProtos.Guest guest(int id, String f, String email, Collection<String> emails) {
        Collection<GuestProtos.Guest.PhoneNumber> phoneNumbers =
                emails.stream().map(e -> GuestProtos.Guest.PhoneNumber.newBuilder()
                        .setType(GuestProtos.Guest.PhoneType.HOME)
                        .setNumber(e).build())
                       .collect(Collectors.toList());

        return GuestProtos.Guest.newBuilder()
                .setName(f)
                .setId(id)
                .setEmail(email)
                .addAllPhones(phoneNumbers)
                .build();
    }


    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);

        return mapper;
    }
}




