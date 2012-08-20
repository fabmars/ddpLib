package org.mars.ddp.common;

import java.io.IOException;
import java.io.InputStream;

/**
 * http://en.wikipedia.org/wiki/Pulse-code_modulation
 */
public class PcmInputStream extends InputStream {

  private InputStream in;
  private int start;
  private int length; //bytes length incl CIRC+Control = 24 bytes/frame
  private int pos;
  
  /**
   * @param in
   * @param start
   * @param length is WITH the CIRC/Control bytes
   * @throws IOException
   */
  public PcmInputStream(InputStream in, int start, int length) throws IOException {
    this.in = in;
    this.start = start;
    this.length = length;
    in.skip(start); //no need to increment pos, so directly in.skip
  }
  
  public int getStart() {
    return start;
  }

  public int getLength() {
    return length;
  }
  
  public int getPos() {
    return pos;
  }
  
  @Override
  public int available() throws IOException {
    return Math.min(length - pos, in.available());
  }

  @Override
  public synchronized int read() throws IOException {
    if(pos < length) { //checking we won't hit the end of the track
      int b = in.read();
      pos++;
      return b;
    }
    else {
      return -1;
    }
  }

  @Override
  public long skip(long n) throws IOException {
    long skipped = in.skip(n);
    pos += skipped;
    return skipped;
  }

  @Override
  public void close() throws IOException {
    in.close();
  }
}