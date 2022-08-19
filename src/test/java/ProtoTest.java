import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ProtoTest {


    public static void main(String [] args) throws IOException {
          serialize();
          GuestProtos.Guest guest= deserialize();
          System.out.println(guest.getId()+"\n"+guest.getName()+"\n"+guest.getEmail());
    }

    public static void serialize() throws IOException {
        FileOutputStream output = new FileOutputStream("testProto.txt");
        DataOutputStream dataOutputStream = new DataOutputStream(output);
        GuestProtos.Guest builder= GuestProtos.Guest.newBuilder()
                .setId(1)
                .setEmail("r***@gmail.com")
                .setName("Walter white")
                .build();

        builder.writeTo(dataOutputStream);
    }
    public static GuestProtos.Guest deserialize() throws IOException {
        GuestProtos.Guest  deserialize =
                GuestProtos.Guest.newBuilder().mergeFrom(new FileInputStream("testProto.txt")).build();
        return deserialize;
    }
}
