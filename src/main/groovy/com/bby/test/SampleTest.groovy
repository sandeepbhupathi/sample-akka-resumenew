package com.bby.test

import akka.actor.AbstractActor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.OneForOneStrategy
import akka.actor.Props
import akka.actor.SupervisorStrategy
import akka.japi.pf.ReceiveBuilder
import akka.pattern.Backoff
import akka.pattern.BackoffSupervisor
import akka.util.Timeout
import scala.Function
import scala.concurrent.duration.Duration
import scala.concurrent.duration.FiniteDuration

import java.lang.annotation.Annotation
import java.util.concurrent.CompletionStage
import java.util.concurrent.TimeUnit
//import java.util.function.Function

/**
 * Created by a1333536 on 5/30/17.
 */



/*ActorSystem system = ActorSystem.create("MySystem");
*//*ActorRef ref = system.actorOf(Props.create(SampleTestNew.class),'MutliSample');*//*



final Props childProps = Props.create(SampleTestNew.class);

*//*final Props  supervisorProps = BackoffSupervisor.props(
        Backoff.onFailure(
                childProps,
                "myEcho",
                Duration.create(3, TimeUnit.SECONDS),
                Duration.create(30, TimeUnit.SECONDS),
                0.2).withSupervisorStrategy(new OneForOneStrategy(3,))); // adds 20% "noise" to vary the intervals slightly*//*


Props backOffProps = BackoffSupervisor.props(
        Backoff.onFailure(
                childProps,
               "SampleTestRestart",
                Duration.create(3, TimeUnit.SECONDS),
                Duration.create(30, TimeUnit.MINUTES),
                0.20
        ).withSupervisorStrategy(OneForOneStrategy.apply(3,Duration.create(3, TimeUnit.SECONDS),true,new Function<Throwable, SupervisorStrategy.Directive>() {
            @Override
            public SupervisorStrategy.Directive apply(Throwable t) throws Exception {
                    return OneForOneStrategy.restart();
            }
        })));

ActorRef backOffActorRef = system.actorOf(backOffProps);
*//*CompletionStage<Object> myActorFuture = ask(backOffActorRef, BackoffSupervisor.getCurrentChild(),
        Timeout.apply(50, TimeUnit.MILLISECONDS));*//*

//return ((CurrentChild) eventFuture.toCompletableFuture().get(60, TimeUnit.MILLISECONDS)).ref().get();


//system.actorOf(supervisorProps, "echoSupervisor")

ActorRef ref = system.actorOf(Props.create(SampleTestNew.class),'MutliSample')

ref.tell(1,ActorRef.noSender());

system.terminate()

System.out.println(".........")*/


