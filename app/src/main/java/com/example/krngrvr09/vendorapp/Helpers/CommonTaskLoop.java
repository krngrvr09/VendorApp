package com.example.krngrvr09.vendorapp.Helpers;

/**
<<<<<<< HEAD
 * Created by krngrvr09 on 23/11/15.
=======
 * Created by MananWason on 14/11/2015.
>>>>>>> 5f805bbc85a71856f1679a4b87db1cd5e146ee77
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
