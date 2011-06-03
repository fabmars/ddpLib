package org.mars.ddp.v101;

import org.mars.ddp.common.AbstractDdpImage;
import org.mars.ddp.common.AbstractDdpImageLoader;

public class DdpImage extends AbstractDdpImage<DdpId, MapPacket> {

  @Override
  public Class<? extends AbstractDdpImageLoader<DdpId, MapPacket>> getLoaderClass() {
    return DdpImageLoader.class;
  }
  
  @Override
  public MapPacket getMainDataPacket() {
    return getDataStreamPacket(DataStreamType.Data_Stream);
  }
  
  @Override
  public MapPacket getPQSubCodePacket() {
    return getSubCodePacket(SubCodeDescriptor.PQ_DESCR);
  }
}
