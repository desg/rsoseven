package rsoseven.lib.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ClientWriter {

	public ClientWriter(byte[] clientData, String name) throws IOException {
		FileOutputStream fileOutput = new FileOutputStream(name);
		fileOutput.write(clientData, 0, clientData.length);
		fileOutput.close();
	}
}
