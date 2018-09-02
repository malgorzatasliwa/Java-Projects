//Created by Malgorzata Sliwa 2018

package com.agh.project;

import com.agh.project.controller.TreasureController;
import com.agh.project.model.Treasure;
import com.agh.project.repository.TreasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class TreasureControllerTest {


    @InjectMocks
    private TreasureController treasureController;

    @Mock
    private TreasureRepository treasureRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetTreasure(){
        Treasure tr = new Treasure();
        tr.setId(1L);
        when(treasureRepository.findOne(1L)).thenReturn(tr);

        Treasure treasure = treasureController.get(1L);

        verify(treasureRepository).findOne(1L);
        assertEquals(1L, treasure.getId().longValue());
        assertThat(treasure.getId(), is(1L));
    }

    @Test
    public void shouldGetNotEmptyListOfTrasures(){
        List<Treasure> treasureList = new ArrayList<Treasure>();
        Treasure tr1 = new Treasure();
        Treasure tr2 = new Treasure();
        treasureList.add(tr1);
        treasureList.add(tr2);
        when(treasureRepository.findAll()).thenReturn(treasureList);

        treasureList = treasureController.list();

        assertEquals(2, treasureList.size());
        assertNotNull(treasureList);
    }

    @Test
    public void shouldPostTreasure(){
        Treasure tr = new Treasure();
        tr.setId(1L);
        when(treasureRepository.findOne(1L)).thenReturn(tr);

        treasureController.create(tr);

        verify(treasureRepository).saveAndFlush(tr);
    }

    @Test
    public void shouldPutTreasure(){
        Treasure tr = new Treasure();
        tr.setId(2L);
        when(treasureRepository.findOne(2L)).thenReturn(tr);

        treasureController.update(2L, tr);

        verify(treasureRepository).findOne(2L);
    }

    @Test
    public void shouldDeleteTreasure(){
        Treasure tr = new Treasure();
        tr.setId(1L);
        when(treasureRepository.findOne(1L)).thenReturn(tr);

        treasureController.delete(1L);

        verify(treasureRepository).findOne(1L);
    }
}
