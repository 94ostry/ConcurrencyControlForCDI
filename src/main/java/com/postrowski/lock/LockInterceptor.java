package com.postrowski.lock;

import javax.ejb.LockType;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Lock
@Interceptor
public class LockInterceptor
{

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock( true );

    @AroundInvoke
    public Object concurrencyControl( InvocationContext ctx ) throws Exception
    {
        Lock lockAnnotation = getLock( ctx );

        if( lockAnnotation.value() == LockType.WRITE )
        {
            ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
            try
            {
                writeLock.lock();
                return ctx.proceed();
            }
            finally
            {
                writeLock.unlock();
            }
        }

        if( lockAnnotation.value() == LockType.READ )
        {
            ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
            try
            {
                readLock.lock();
                return ctx.proceed();
            }
            finally
            {
                readLock.unlock();
            }
        }

        throw new IllegalStateException( "EEEEEEEEE" );
    }

    private Lock getLock( InvocationContext ctx )
    {
        Lock lockAnnotation = ctx.getMethod().getAnnotation( Lock.class );

        if( lockAnnotation == null )
        {
            lockAnnotation = ctx.getTarget().getClass().getAnnotation( Lock.class );
        }
        return lockAnnotation;
    }
}