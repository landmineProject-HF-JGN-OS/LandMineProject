package com.mrjaffesclass.apcs.mvc.template;

import com.mrjaffesclass.apcs.messenger.*;
import java.util.*;

/**
 * The model represents the data that the app uses.
 * @author Roger Jaffe
 * @version 1.0
 */
public class Model implements MessageHandler {

  // Messaging system for the MVC
  private final Messenger mvcMessaging;

  // Model's data variables
  private boolean bombMap[] = new boolean[64];

  /**
   * Model constructor: Create the data representation of the program
   * @param messages Messaging class instantiated by the Controller for 
   *   local messages between Model, View, and controller
   */
  public Model(Messenger messages) {
    mvcMessaging = messages;
  }
  
  /**
   * Initialize the model here and subscribe to any required messages
   */
  public void init() {
    mvcMessaging.subscribe("view:changeButton", this);
    setBombmap();
  }
  
  @Override
  public void messageHandler(String messageName, Object messagePayload) {
    if (messagePayload != null) {
      System.out.println("MSG: received by model: "+messageName+" | "+messagePayload.toString());
    } else {
      System.out.println("MSG: received by model: "+messageName+" | No data sent");
    }
    MessagePayload payload = (MessagePayload)messagePayload;
    int field = payload.getField();
    int direction = payload.getDirection();
    
    if (direction == Constants.UP) {
      if (field == 1) 
      {
          setBombmap();
      }   
    }
  }

  /**
   * Getter function for variable 1
   * @return Value of variable1
   */
  public boolean[] getBombmap() {
    return bombMap;
  }

  /**
   * Setter function for variable 1
   * @param v New value of variable1
   */
  public void setBombmap() {
    
    Random aa = new Random();
    for(int i = 0; i != bombMap.length - 1; i++)
    {
        bombMap[i] = aa.nextBoolean();
    
    // When we set a new value to variable 1 we need to also send a
    // message to let other modules know that the variable value
    // was changed
        mvcMessaging.notify("model:isBomb", bombMap[i], true);
    }
  }
}
