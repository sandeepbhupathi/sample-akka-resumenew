package com.sample.file;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

/**
 * Created by a1333536 on 5/31/17.
 */
public class FileWriteParent extends AbstractActor{

    private ActorRef actorRef;

    @Override
    public void preStart(){
        actorRef = context().actorOf(Props.create(FileWriteChild.class));
    }
    @Override
    public Receive createReceive() {
        return receiveBuilder().match(String.class,message->{
            //for(int i=0;i<10;i++){
            actorRef.tell(100,ActorRef.noSender());
            actorRef.tell(100,ActorRef.noSender());
            //}
        }).build();
    }

}
