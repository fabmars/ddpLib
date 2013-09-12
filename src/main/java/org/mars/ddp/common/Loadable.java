package org.mars.ddp.common;

import java.net.URL;

public interface Loadable<T> {
  public <P extends T> Loader<P> newLoader(URL baseUrl, String fileName) throws DdpException;
}
