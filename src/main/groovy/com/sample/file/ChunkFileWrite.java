package com.sample.file;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Created by a1333536 on 5/31/17.
 */
public class ChunkFileWrite {
    public static void main(String args[]){
        ActorSystem system = ActorSystem.apply("FileWriteSample");
        ActorRef actorRef = system.actorOf(Props.create(FileWriteParent.class),"FileWriterParent");

        actorRef.tell("start",ActorRef.noSender());


        system.terminate();
    }
}
