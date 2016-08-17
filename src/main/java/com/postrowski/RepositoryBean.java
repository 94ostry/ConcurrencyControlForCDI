package com.postrowski;

import com.postrowski.lock.Lock;

import javax.ejb.LockType;
import javax.enterprise.context.ApplicationScoped;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by postrowski on 2016-08-17.
 */


@ApplicationScoped
public class RepositoryBean
{
    List<String> states = new ArrayList<>();

    @Lock
    public void clear()
    {
        states.clear();
    }

    @Lock( LockType.READ  )
    public boolean isEmpty()
    {
        return states.isEmpty();
    }

    @Lock
    public void add()
    {
        final String time = LocalTime.now().toString();

        System.out.println( "BEFORE " + Thread.currentThread().getName() );

        states.add(  "V1_" + time + "___" + Thread.currentThread().getName() );

        sleep( 5 );

        states.add(  "V1_" + time + "___" + Thread.currentThread().getName() );

        System.out.println( "AFTER " + Thread.currentThread().getName() );
    }


    private void sleep( long i )
    {
        try
        {
            TimeUnit.SECONDS.sleep( i );
        }
        catch( Exception e )
        {

        }
    }
}
