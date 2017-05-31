package com.sample.test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;

import static akka.pattern.Patterns.ask;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by sande on 5/31/2017.
 */
public class CouponSample {
    public static void main(String args[]) throws Exception{
        ActorSystem system = ActorSystem.create("FaultHandlingTest");
        Duration timeout = Duration.create(5, SECONDS);

        Props superprops = Props.create(ParentActor.class);
        ActorRef supervisor = system.actorOf(superprops, "supervisor");
        ActorRef child = (ActorRef) Await.result(ask(supervisor,
                Props.create(ChildActor.class), 5000), timeout);

        child.tell(10, ActorRef.noSender());
        for(int i=0;i<10;i++){
            if(!Await.result(ask(child, "get", 5000), timeout).equals(10)) {
                System.out.println(".....resumeing..." + i);
                child.tell(10, ActorRef.noSender());
            }else{
                System.out.println("done with creating coupons");
                break;
            }
        }


         system.terminate();
    }
}
