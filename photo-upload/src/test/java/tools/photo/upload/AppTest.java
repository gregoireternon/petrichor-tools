package tools.photo.upload;

import org.junit.Assert;
import org.junit.Test;
import petrichor.network.GNetworkHelper;
import petrichor.network.GNetworkServiceImplementation;
import petrichor.network.GPath;

/**
 * Unit test for simple App.
 */
public class AppTest 

{
    @Test
    public void GNetworkHelprTest() throws Exception {
        Assert.assertEquals(GNetworkHelper.getMethodPath(TestInterface.class.getMethod("m1"),null),"baba");
        Assert.assertEquals(GNetworkHelper.getMethodPath(TestInterface.class.getMethod("m2"),null),"baba");
        Assert.assertEquals(GNetworkHelper.getMethodPath(TestInterface.class.getMethod("m3"),null),"baba");
        Assert.assertEquals(GNetworkHelper.getMethodPath(TestInterface.class.getMethod("m4"),"a"),"baba/a/re");
    }

    private interface TestInterface{
        @GPath("baba")
        void m1();

        @GPath("/baba")
        void m2();

        @GPath("/baba/")
        void m3();

        @GPath("/baba/{}/re")
        void m4();
    }
}
