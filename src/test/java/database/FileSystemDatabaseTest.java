package database;

import examples.database.impl.FileSystemUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FileSystemUtils.class)
public class FileSystemDatabaseTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setUp() {
		PowerMockito.mockStatic(FileSystemUtils.class);
	}

	@Test
	public void givenIdWhenFileExistsThenReturnObjectFromFile() throws Exception {
		when(FileSystemUtils.exists(anyString(), any())).thenReturn(true);

	}

	@Test
	public void givenIdWhenFileNotExistsThenThrowException() {
		when(FileSystemUtils.exists(anyString(), any())).thenReturn(true);

	}

}
