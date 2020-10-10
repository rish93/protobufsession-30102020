package org.processor.proto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.processor.proto.model.Guest;
import org.processor.proto.model.GuestProtos;
import org.processor.proto.model.PhoneNumber;
import org.processor.proto.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;


@RestController
class GuestController{

    @Autowired
    private GuestRepository guestRepository;


    @RequestMapping(value = "/customers/proto/{id}",method = RequestMethod.GET)
    private GuestProtos.Guest getGuestProto(@PathVariable  Integer id){
        GuestProtos.Guest guest= this.guestRepository.findById(id);
        return guest;
    }

    @RequestMapping(value = "/customers/json/{id}",method = RequestMethod.GET)
    private Guest serializeGuestJson(@PathVariable  Integer id) throws IOException {
          PhoneNumber phoneNumber=  PhoneNumber.builder()
            .number("2354455")
            .type("HOME")
            .build();
          Guest guest=  Guest.builder()
            .id(id)
            .email("dy@gmail.com")
            .name("Eon")
            .phoneNumbers(new PhoneNumber[]{phoneNumber})
            .build();

        return guest;
    }


    /*
{
    "name": "Eon",
    "id": 1,
    "email": "dy@gmail.com",
    "phoneNumbers": [
        {
            "number": "2354455",
            "type": "HOME"
        }
    ]
}
*/
    @RequestMapping(value = "/customers/json/{id}", method = RequestMethod.POST)
    private Guest serializeGuest(@PathVariable  Integer id, @RequestBody Guest guest) throws IOException {

        long startTime = System.currentTimeMillis();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("src/main/resources/SerializeJSON"+new Date().getSeconds()+".json"), guest );
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("estimatedTime JSON serialize "+estimatedTime);
        return guest;
    }

    @RequestMapping(value = "/customers/proto/{id}",method = RequestMethod.POST)
    private GuestProtos.Guest serializeGuestProto(@PathVariable  Integer id) throws IOException {
        GuestProtos.Guest guest=this.guestRepository.findById(id);
        long startTime = System.currentTimeMillis();
        FileOutputStream fos = new FileOutputStream("src/main/resources/serializedTestProto"+new Date().getSeconds());
        guest.writeTo(fos);
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("estimatedTime PROTO serialize "+estimatedTime);
        return guest;
    }
}
