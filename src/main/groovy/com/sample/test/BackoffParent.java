package com.sample.test;

import akka.actor.*;
import akka.japi.JavaPartialFunction;
import akka.japi.pf.DeciderBuilder;
import akka.pattern.Backoff;
import akka.pattern.BackoffSupervisor;
import com.bby.test.SampleTestNew;
import scala.Function;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

import static akka.actor.SupervisorStrategy.escalate;
import static akka.actor.SupervisorStrategy.resume;
import static akka.pattern.Patterns.ask;
import static java.util.concurrent.TimeUnit.SECONDS;
import static akka.pattern.Patterns.ask;
/**
 * Created by a1333536 on 5/31/17.
 */
public class BackoffParent {

    public static void main(String args[]) throws Exception{
        ActorSystem system = ActorSystem.create("MySystem");
        Props childProps = Props.create(ChildActor.class);
        Duration timeout = Duration.create(5, SECONDS);

        Props backOffProps = BackoffSupervisor.props(
                Backoff.onFailure(
                        childProps,
                        "SampleTestRestart",
                        Duration.create(3, TimeUnit.SECONDS),
                        Duration.create(30, TimeUnit.MINUTES),
                        0.20
                ).withSupervisorStrategy(OneForOneStrategy.apply(3, Duration.create(3, TimeUnit.SECONDS), true, DeciderBuilder.
                        match(Exception.class, e -> resume()).
                        matchAny(o -> escalate()).build())));

        ActorRef backOffActorRef = system.actorOf(backOffProps);

        backOffActorRef.tell(10, ActorRef.noSender());
        for(int i=0;i<10;i++){
            if(!Await.result(ask(backOffActorRef, "get", 5000), timeout).equals(10)) {
                System.out.println(".....resumeing..." + i);
                backOffActorRef.tell(10, ActorRef.noSender());
            }else{
                System.out.println("done with creating coupons");
                break;
            }
        }

        system.terminate();


    }
}
