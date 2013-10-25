package org.mars.ddp.v20;

import java.io.IOException;
import java.net.URL;

import org.mars.ddp.common.AbstractDdpId;
import org.mars.ddp.common.AbstractDdpImage;
import org.mars.ddp.common.AbstractDdpImageLoader;
import org.mars.ddp.common.DdpException;
import org.mars.ddp.common.MapStream;
import org.mars.ddp.v20.MapStreamLoader;

public class DdpImageLoader extends AbstractDdpImageLoader {

  public DdpImageLoader(URL baseUrl) {
    super(baseUrl);
  }

  /**
   * Caution: same code as in v101 package. Change one, change the other!
   */
  @Override
  protected void load(AbstractDdpImage image) throws IOException, DdpException {
    DdpIdLoader ddpIdLoader = new DdpIdLoader(getBaseUrl(), AbstractDdpId.STREAM_NAME);
    image.setDdpId(ddpIdLoader.load(true));
    
    MapStreamLoader mapStreamLoader = new MapStreamLoader(getBaseUrl(), MapStream.STREAM_NAME);
    image.setMapStream(mapStreamLoader.load(true));
  }


  @Override
  public AbstractDdpImage spawn(URL streamUrl) throws DdpException {
    return new DdpImage();
  }
}
