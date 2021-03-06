package com.suntriprecords.ddp.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import com.suntriprecords.ddp.common.AbstractDdpId;
import com.suntriprecords.ddp.common.AbstractDdpIdLoader;
import com.suntriprecords.ddp.common.AbstractDdpImage;
import com.suntriprecords.ddp.common.AbstractDdpImageLoader;
import com.suntriprecords.ddp.common.DdpException;
import com.suntriprecords.ddp.common.DdpLevel;
import com.suntriprecords.ddp.common.MapStream;


public class DdpImageFactory {

  public static AbstractDdpImage load(URL imageDirUrl) throws DdpException, IOException {
    DdpLevel level = readDdpLevel(imageDirUrl);

    try {
      Class<? extends AbstractDdpImageLoader> loaderClass = level.getLoaderClass();
      Constructor<? extends AbstractDdpImageLoader> ctor = loaderClass.getConstructor(URL.class);
      AbstractDdpImageLoader loader = ctor.newInstance(imageDirUrl);
      
      return loader.load(true);
    }
    catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      throw new DdpException(e);
    }
    
  }
  
  public static AbstractDdpImage load(Path imageDir) throws DdpException, IOException {
    return load(imageDir.toUri().toURL());
  }  

  public static AbstractDdpImage load(File imageDir) throws DdpException, IOException {
    return load(imageDir.toURI().toURL());
  }
  
  
  
  public static DdpLevel readDdpLevel(URL imageDirUrl) throws IOException {
    URL ddpIdUrl = new URL(imageDirUrl.toExternalForm() + AbstractDdpId.STREAM_NAME);
    return AbstractDdpIdLoader.readDdpLevel(ddpIdUrl);
  }
  
  public static DdpLevel readDdpLevel(File imageDir) throws IOException {
    return readDdpLevel(imageDir.toPath());
  }
  
  public static DdpLevel readDdpLevel(Path imagePath) throws IOException {
    return readDdpLevel(imagePath.toUri().toURL());
  }

  
  
  public static boolean isDDPImage(URL imageUrl) {
      String imagePath = imageUrl.toExternalForm();
      try {
        URL ddpIdUrl = new URL(imagePath + AbstractDdpId.STREAM_NAME);
        ddpIdUrl.openStream().close();
        URL ddpMsUrl = new URL(imagePath + MapStream.STREAM_NAME);
        ddpMsUrl.openStream().close();
        return true;
      }
      catch (IOException e) {
        return false;
      }
  }
  
  public static boolean isDDPImage(File imageDir) {
    return isDDPImage(imageDir.toPath());
  }
  
  public static boolean isDDPImage(Path imagePath) {
    Path ddpIdPath = imagePath.resolve(AbstractDdpId.STREAM_NAME);
    Path ddpMsPath = imagePath.resolve(MapStream.STREAM_NAME);
    return Files.exists(ddpIdPath) && Files.exists(ddpMsPath);
  }
}
