**PROTOBUF SESSION:** 
30102020

Protobuf is short for protocol buffers, which are language- and platform-neutral mechanisms for serializing structured data

You define how you want your data to be structured once, then you can use special generated source code to easily write and read your structured data to and from a variety of data streams and using a variety of languages.

You can even update your data structure without breaking deployed programs that are compiled against the “old” format 


End Point Exposed in application:

http://localhost:8080/customers/proto/1
POST

http://localhost:8080/customers/json/1
POST
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
