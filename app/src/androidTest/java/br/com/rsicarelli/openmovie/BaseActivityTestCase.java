package br.com.rsicarelli.openmovie;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.test.ActivityTestCase;

import org.junit.Before;

public class BaseActivityTestCase extends ActivityTestCase {

    protected Instrumentation.ActivityMonitor monitor;

    public Instrumentation getInstrumentation() {
        return InstrumentationRegistry.getInstrumentation();
    }

    @Before
    public void setUp() {
        registerMonitor(getActivityMonitor());
    }

    public Class<? extends Activity> getActivityMonitor() {
        return DumbActivity.class;
    }

    public void registerMonitor(Class<? extends Activity> activityClass) {
        removeMonitor();
        monitor = new Instrumentation.ActivityMonitor(activityClass.getName(), null, false);
        getInstrumentation().addMonitor(monitor);
    }

    public void removeMonitor() {
        if (monitor != null) {
            getInstrumentation().removeMonitor(monitor);
        }
    }
}
