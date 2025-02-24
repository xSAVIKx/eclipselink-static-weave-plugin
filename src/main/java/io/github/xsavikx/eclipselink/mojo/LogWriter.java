/**
 *
 */
package io.github.xsavikx.eclipselink.mojo;

import org.apache.maven.plugin.logging.Log;

import java.io.StringWriter;

/**
 * Needed to be able to write to maven logfile.
 *
 * @author Christoph Guse
 */
public class LogWriter extends StringWriter {

  private Log log;

  private final String LINEBREAK = "\r\n";

  private StringBuilder logLineBuilder = new StringBuilder();

  public LogWriter(Log log) {
    super();
    this.log = log;
  }

  @Override
  public void write(String str) {
    if (!LINEBREAK.equals(str)) {
      logLineBuilder.append(str);
    } else {
      log.info(logLineBuilder);
      logLineBuilder.delete(0, logLineBuilder.length());
    }
  }
}
