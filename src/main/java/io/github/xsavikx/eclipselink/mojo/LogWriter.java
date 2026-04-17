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

  private final Log log;

  private final StringBuilder logLineBuilder = new StringBuilder();

  public LogWriter(Log log) {
    super();
    this.log = log;
  }

  @Override
  public void write(String str) {
    write(str, 0, str.length());
  }

  @Override
  public void write(String str, int off, int len) {
    for (int i = off; i < off + len; i++) {
      write(str.charAt(i));
    }
  }

  @Override
  public void write(char[] cbuf, int off, int len) {
    for (int i = off; i < off + len; i++) {
      write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) {
    if (c == '\r') {
      return;
    }
    if (c == '\n') {
      flushLine();
      return;
    }
    logLineBuilder.append((char) c);
  }

  @Override
  public void flush() {
    flushLine();
  }

  @Override
  public void close() {
    flushLine();
  }

  private void flushLine() {
    if (logLineBuilder.length() == 0) {
      return;
    }
    log.info(logLineBuilder.toString());
    logLineBuilder.setLength(0);
  }
}
