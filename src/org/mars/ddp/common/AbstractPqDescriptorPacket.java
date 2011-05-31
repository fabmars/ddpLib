package org.mars.ddp.common;

public abstract class AbstractPqDescriptorPacket extends AbstractPacket {

  public final static String STREAM_NAME = "PQDESCR";
  public static String SUBCODE_PACKET_VALID = "VVVS";
  public final static int PACKET_LENGTH = 64;

  private String trackNumber; //[00-99] except lead-out: AA
  private Integer indexNumber; //index number associated with the A-time entries in this packet
  private Integer cdaTimeHours; //Reserved
  private Integer cdaTimeMinutes;
  private Integer cdaTimeSeconds; //If trackNumber is 00h and indexNumber is A0h, this field contains the Psec value for the CD
  private Integer cdaTimeFrames;
  private String controlByte1; //upper byte: control for the entry. Lower byte: either 1 for a normal entry or S if Serial Copy Management System is to be used
  private String controlByte2; //Reserved
  private String isrc; //valid only for the first entry for each track number greater than 0
  private String upcEan; //Only one UPC entry is allowed for each PQ packet stream. It is recommended that it be placed in the first packet.
  private String userText; //user comments that will not be recorded to the CD
  
  
  public String getTrackNumber() {
    return trackNumber;
  }
  public Integer getIndexNumber() {
    return indexNumber;
  }
  public Integer getCdaTimeHours() {
    return cdaTimeHours;
  }
  public Integer getCdaTimeMinutes() {
    return cdaTimeMinutes;
  }
  public Integer getCdaTimeSeconds() {
    return cdaTimeSeconds;
  }
  public Integer getCdaTimeFrames() {
    return cdaTimeFrames;
  }
  public String getControlByte1() {
    return controlByte1;
  }
  public String getControlByte2() {
    return controlByte2;
  }
  public String getIsrc() {
    return isrc;
  }
  public String getUpcEan() {
    return upcEan;
  }
  public String getUserText() {
    return userText;
  }
  public void setTrackNumber(String trackNumber) {
    this.trackNumber = trackNumber;
  }
  public void setIndexNumber(Integer indexNumber) {
    this.indexNumber = indexNumber;
  }
  public void setCdaTimeHours(Integer cdaTimeHours) {
    this.cdaTimeHours = cdaTimeHours;
  }
  public void setCdaTimeMinutes(Integer cdaTimeMinutes) {
    this.cdaTimeMinutes = cdaTimeMinutes;
  }
  public void setCdaTimeSeconds(Integer cdaTimeSeconds) {
    this.cdaTimeSeconds = cdaTimeSeconds;
  }
  public void setCdaTimeFrames(Integer cdaTimeFrames) {
    this.cdaTimeFrames = cdaTimeFrames;
  }
  public void setControlByte1(String controlByte1) {
    this.controlByte1 = controlByte1;
  }
  public void setControlByte2(String controlByte2) {
    this.controlByte2 = controlByte2;
  }
  public void setIsrc(String isrc) {
    this.isrc = isrc;
  }
  public void setUpcEan(String upcEan) {
    this.upcEan = upcEan;
  }
  public void setUserText(String userText) {
    this.userText = userText;
  }
  
  /**
   * @see http://en.wikipedia.org/wiki/Compact_Disc#Data_structure
   */
  public Integer getCdaCueSeconds() {
    int hours = (cdaTimeHours != null ? cdaTimeHours : 0); //usually empty (reserved)
    return ((hours * 60 + cdaTimeMinutes) * 60 + cdaTimeSeconds);
  }

  /**
   * At 75 frames per second
   * @see http://en.wikipedia.org/wiki/Compact_Disc#Data_structure
   */
  public Integer getCdaCueFrames() {
    return getCdaCueSeconds() * 75 + cdaTimeFrames;
  }

  /**
   * At 33 bytes per frame
   * @see http://en.wikipedia.org/wiki/Compact_Disc#Data_structure
   */
  public Integer getCdaCueBytes() {
    return getCdaCueFrames() * 33;
  }
}
