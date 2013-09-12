package org.mars.ddp.v101;

import java.net.URL;

import org.mars.ddp.common.AbstractDdpIdLoader;
import org.mars.ddp.common.DdpException;

public class DdpIdLoader extends AbstractDdpIdLoader<DdpId> {

  public DdpIdLoader(URL baseUrl, String fileName) {
    super(baseUrl, fileName);
  }

  @Override
  public DdpId newLoadable() throws DdpException {
    return new DdpId();
  }
}
