package com.bby.test;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

/**
 * Created by a1333536 on 5/30/17.
 */
public class SampleTestNew extends AbstractActor{
    @Override
    public Receive createReceive() {
        return receiveBuilder().match(Integer.class,retryCount -> {
            System.out.println("retry Count"+retryCount);
            throwException();
        }).build();
    }

    private void throwException() throws Exception{
        throw new RuntimeException();
    }
    //getContext().actorOf(Props.create(SampleTwo.class),"ChildActor").tell("Sample", ActorRef.noSender());
    /*public void postStop() throws Exception {
        System.out.println("Stopping");
        this.postStop();
    }*/

}
