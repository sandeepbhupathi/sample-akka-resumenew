package com.bby.test;

import akka.actor.AbstractActor;

/**
 * Created by a1333536 on 5/30/17.
 */
public class SampleTwo extends AbstractActor {


    @Override
    public Receive createReceive() {
        return receiveBuilder().match(String.class,str ->{
            System.out.println("Sample Child Actor"+str);
            Thread.sleep(10000);

            getSender().tell("work up",getSelf());
        }).build();
    }
}
