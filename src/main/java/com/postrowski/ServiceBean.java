package com.postrowski;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by postrowski on 2016-08-17.
 */
@Stateless
public class ServiceBean
{
    @Inject
    private RepositoryBean repositoryBean;

    public void add()
    {
        repositoryBean.add();
    }

    public boolean isEmpty()
    {
        return repositoryBean.isEmpty();
    }

    public void clear()
    {
        repositoryBean.clear();
    }
}
