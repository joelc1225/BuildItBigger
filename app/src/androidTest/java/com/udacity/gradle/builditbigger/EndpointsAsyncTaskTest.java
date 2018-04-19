package com.udacity.gradle.builditbigger;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest {

    @Test
    public void testAsynctaskReturnsValidString() throws InterruptedException, ExecutionException, TimeoutException {

        // creates asyncTask that we created specifically for testing
        EndpointsAsyncTaskTESTING asyncTask = new EndpointsAsyncTaskTESTING();
        // starts task
        asyncTask.execute(InstrumentationRegistry.getContext());
        // assigns value to variable after a few seconds
        String jokeString = asyncTask.get(5, TimeUnit.SECONDS);
        // checks if the value returned is not null
        Assert.assertNotNull(jokeString);

    }
}