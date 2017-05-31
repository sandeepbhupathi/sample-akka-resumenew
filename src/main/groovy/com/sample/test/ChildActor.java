package com.sample.test;

import akka.actor.AbstractActor;

/**
 * Created by sande on 5/31/2017.
 */
public class ChildActor extends AbstractActor {

    private int numberOfCouponsCreated =0;

    @Override
    public Receive createReceive() {
        return receiveBuilder(). match(Integer.class, i -> {
            if(numberOfCouponsCreated!=0){
                i=i-numberOfCouponsCreated;
            }
            for(int j=0;j<i;j++) {
                numberOfCouponsCreated ++;
                System.out.println("creating coupons number: "+numberOfCouponsCreated);
                if(j==2){
                    throw new Exception();
                }
            }
        }).matchEquals("get", s -> sender().tell(numberOfCouponsCreated, self())).build();
    }
}
