//Created by Malgorzata Sliwa 2018

package com.agh.project;

import com.agh.project.controller.BasicController;
import org.junit.Assert;
import org.junit.Test;

public class AppTest {

    @Test
    public void testApp(){
        BasicController bc = new BasicController();
        String result = bc.basic();
        Assert.assertEquals(result, "Start Application");

    }
}
