package com.example.krngrvr09.vendorapp.Helpers;

/**
 * Created by krngrvr09 on 23/11/15.
 */
public class CommonTaskLoop {
    private static CommonTaskLoop ourInstance = new CommonTaskLoop();

    private CommonEventLoop m_loop;

    private CommonTaskLoop() {
        m_loop = new CommonEventLoop();
    }

    public static CommonTaskLoop getInstance() {
        return ourInstance;
    }

    public void post(Runnable call) {
        m_loop.post(call);
    }

    public void delayPost(Runnable call, int nMillSec) {
        m_loop.delayPost(call, nMillSec);
    }

    public void shutdown() {
        m_loop.shutdown();
    }
}
