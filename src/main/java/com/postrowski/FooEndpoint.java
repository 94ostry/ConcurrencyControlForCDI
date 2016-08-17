package com.postrowski;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by postrowski on 2016-07-26.
 */
@Path( "/foo" )
public class FooEndpoint
{
    @Inject
    private ServiceBean serviceBean;

    @GET
    @Path( "/add" )
    public Response add()
    {
        serviceBean.add();

        return Response.ok("add dobe").build();
    }

    @GET
    @Path( "/empty" )
    public Response empty()
    {
        boolean isEmpty = serviceBean.isEmpty();

        return Response.ok( Boolean.valueOf( isEmpty ).toString() ).build();
    }


    @GET
    @Path( "/clear" )
    public Response clear()
    {
        serviceBean.clear();

        return Response.ok("clear done").build();
    }



}
