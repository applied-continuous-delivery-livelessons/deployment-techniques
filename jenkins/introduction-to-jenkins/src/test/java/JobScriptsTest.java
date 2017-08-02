import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

import javaposse.jobdsl.dsl.DslScriptLoader;
import javaposse.jobdsl.dsl.MemoryJobManagement;
import org.junit.Test;

public class JobScriptsTest {

	@Test
	public void should_compile_scripts() throws IOException {
		// Simulate running Jenkins
		MemoryJobManagement jm = new MemoryJobManagement();
		DslScriptLoader loader = new DslScriptLoader(jm);
		String scriptText = new String(Files.readAllBytes(
				new File("course/job-dsl.groovy").toPath()));

		loader.runScript(scriptText);
	}
}
