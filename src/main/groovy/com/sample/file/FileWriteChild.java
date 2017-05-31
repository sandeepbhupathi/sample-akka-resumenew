package com.sample.file;

import akka.actor.AbstractActor;

/**
 * Created by a1333536 on 5/31/17.
 */
public class FileWriteChild extends AbstractActor{

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(Integer.class,numberOfRecords->{

            for (int i=0;i<numberOfRecords;i++){
                System.out.println("--message--"+i);
            }
        }).build();
    }
}
