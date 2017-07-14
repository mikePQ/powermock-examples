package database;

import examples.database.api.DatabaseActionResultWithValue;
import examples.database.api.Identifiable;
import examples.database.impl.FileSystemDatabase;
import examples.database.impl.FileSystemUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FileSystemUtils.class)
@PowerMockIgnore("javax.management.*")
public class FileSystemDatabaseTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setUp() {
		PowerMockito.mockStatic(FileSystemUtils.class);
	}

	@Test
	public void givenIdWhenFileNotExistsThenGetErrorMessage() {
		when(FileSystemUtils.exists(anyString(), any())).thenReturn(false);

		Path dir = Paths.get("dir");
		String id = UUID.randomUUID().toString();

		FileSystemDatabase database = new FileSystemDatabase<>(dir, null, null);
		DatabaseActionResultWithValue result = database.getById(id);

		assertFalse(result.isSuccessful());
		assertEquals("Cannot find file for id: " + id, result.getErrorMessage());
	}

}
