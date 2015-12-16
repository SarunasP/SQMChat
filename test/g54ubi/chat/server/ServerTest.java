package g54ubi.chat.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ServerTest {

	private static final int TEST_PORT = 9000;

	private static Server server;

	@BeforeClass
	public static void setupClass() {
		server = new Server(TEST_PORT);
		new Thread(new Runnable() {
			@Override
			public void run() {
				server.start();
			}
		}).start();
	}

	@Test
	public void testGetUserList() {
		ArrayList<String> userList = server.getUserList();
		assertNotNull(userList);
		assertEquals(0, userList.size());
	}

	@Test
	public void testDoesUserExist() {
		boolean isExists = server.doesUserExist("UnknownUser");
		assertEquals(false, isExists);
	}

	@Test
	public void testBroadcastMessageWithoutError() {
		server.broadcastMessage("Test Message");
		assertTrue(true);
	}

	@Test
	public void testSendPrivateMessage() {
		boolean result = server.sendPrivateMessage("Test Message",
				"UnknownUser");
		assertEquals(false, result);
	}

	@Test
	public void testRemoveDeadUsersWithoutError() {
		server.removeDeadUsers();
		assertTrue(true);
	}

	@Test
	public void testGetNumberOfUsers() {
		int numberOfUsers = server.getNumberOfUsers();
		assertEquals(0, numberOfUsers);
	}

	@AfterClass
	public static void tearDownClass() throws IOException {
		server.finalize();
	}

}
