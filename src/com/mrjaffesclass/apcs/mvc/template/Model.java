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
  //Checks if the message "is bomb" is recieved.
  public void messageHandler(String messageName, Object messagePayload) {
    if (messagePayload != null) {
      System.out.println("MSG: received by model: "+messageName+" | "+messagePayload.toString());
    } else {
      System.out.println("MSG: received by model: "+messageName+" | No data sent");
    }
    MessagePayload payload = (MessagePayload)messagePayload;
    int field = payload.getField();
    int direction = payload.getDirection();
    
     if (messageName.equals("model:isBomb"))
     {
        if ((boolean)messagePayload == true) 
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
   * Setter function for Bombmap
   * @param aa New value of Bombmap
   */
  public void setBombmap() {
    
    Random aa = new Random();
    for(int i = 0; i < 10; i++)
    {
        bombMap[aa.nextInt(64)] = true;
    
    // When we set a new value to variable 1 we need to also send a
    // message to let other modules know that the variable value
    // was changed
        mvcMessaging.notify("model:isBomb", bombMap, true);
    }
  }
}
