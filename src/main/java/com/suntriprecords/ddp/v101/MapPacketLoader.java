package com.suntriprecords.ddp.v101;

import java.io.IOException;
import java.net.URL;

import com.suntriprecords.ddp.common.AbstractMapPacketLoader;
import com.suntriprecords.ddp.common.DataStreamTypeable;
import com.suntriprecords.ddp.common.DdpException;
import com.suntriprecords.ddp.common.TextStreamTypeable;
import com.suntriprecords.ddp.v20.TextStreamType;

public class MapPacketLoader extends AbstractMapPacketLoader<MapPacket> {

  public MapPacketLoader(URL baseUrl, String fileName) {
    super(baseUrl, fileName);
  }

  @Override
  protected void load(MapPacket mapPacket) throws IOException, DdpException {
    super.load(mapPacket);
    readString(37); //padding
  }

  @Override
  public DataStreamType readDataStreamType() throws IOException {
    String id = readString(2, true);
    DataStreamType type = DataStreamType.fromId(id);
    return type;
  }

  @Override
  public SubCodeDescriptor readSubCodeDescriptor() throws IOException {
    SubCodeDescriptor sub = null;
    String id = readString(8, true);
    if(id != null) {
      sub = SubCodeDescriptor.fromId(id);
    }
    return sub;
  }

  @Override
  public SourceStorageMode readSourceStorageMode() throws IOException {
    SourceStorageMode ssm = null;
    Integer id = readIntFromString(1);
    if(id != null) {
      ssm = SourceStorageMode.fromId(id);
    }
    return ssm;
  }

  @Override
  public TextStreamTypeable getTextStreamType(DataStreamTypeable dst) throws IOException {
    if(dst.getType() == DataStreamTypeable.TYPE_TEXT) {
      return TextStreamType.fromId(dst.getId());
    }
    else {
      return null;
    }
  }
  
  @Override
  public MapPacket spawn(URL streamUrl) throws DdpException {
    return new MapPacket();
  }
}
