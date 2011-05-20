package org.mars.ddp.common;

import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractPqDescriptorParser extends AbstractPacketParser<AbstractPqDescriptorPacket> {

  public AbstractPqDescriptorParser(InputStream is) {
    super(is);
  }

  @Override
  protected void parse(AbstractPqDescriptorPacket pqPacket) throws IOException {
    String textPacketValid = readString(4, true);
    if(!AbstractPqDescriptorPacket.SUBCODE_PACKET_VALID.equals(textPacketValid)) {
      throw new IllegalArgumentException("subcodePacketValid = " + textPacketValid);
    }
    
    String trackNumber = readString(2, true);
    pqPacket.setTrackNumber(trackNumber);
    
    Integer indexNumber = readInt(2);
    pqPacket.setIndexNumber(indexNumber);
    
    Integer cdaTimeHours = readInt(2);
    pqPacket.setCdaTimeHours(cdaTimeHours);
    
    Integer cdaTimeMinutes = readInt(2);
    pqPacket.setCdaTimeMinutes(cdaTimeMinutes);
    
    Integer cdaTimeSeconds = readInt(2);
    pqPacket.setCdaTimeSeconds(cdaTimeSeconds);
    
    Integer cdaTimeFrames = readInt(2);
    pqPacket.setCdaTimeFrames(cdaTimeFrames);
    
    String controlByte1 = readString(2, true);
    pqPacket.setControlByte1(controlByte1);
    
    String controlByte2 = readString(2, true);
    pqPacket.setControlByte2(controlByte2);
    
    String isrc = readString(12, true);
    pqPacket.setIsrc(isrc);
    
    String upcEan = readString(13, true);
    pqPacket.setUpcEan(upcEan);
    
    String userText = readString(19, true);
    pqPacket.setUserText(userText);
  }

  @Override
  public String getStreamName() {
    return AbstractPqDescriptorPacket.STREAM_NAME;
  }
}