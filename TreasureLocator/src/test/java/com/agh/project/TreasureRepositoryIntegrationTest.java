//Created by Malgorzata Sliwa 2018

package com.agh.project;

import com.agh.project.model.Treasure;
import com.agh.project.repository.TreasureRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(App.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TreasureRepositoryIntegrationTest {

    @Autowired
    private TreasureRepository treasureRepository;

    @Test
    public void testFindAll(){
        List<Treasure> treasures = treasureRepository.findAll();
        assertThat(treasures.size(), is(greaterThanOrEqualTo(0)));
    }
}

