//Created by Malgorzata Sliwa 2018

package com.agh.project.controller;

import com.agh.project.model.Treasure;
import com.agh.project.repository.TreasureRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class TreasureController {

    @Autowired
    private TreasureRepository treasureRepository;

    @RequestMapping(value = "treasures", method = RequestMethod.GET)
    public List<Treasure> list(){
        return treasureRepository.findAll();
    }

    @RequestMapping(value = "treasures", method = RequestMethod.POST)
    public Treasure create(@RequestBody Treasure treasure){
        return treasureRepository.saveAndFlush(treasure);
    }

    @RequestMapping(value = "treasures/{id}", method = RequestMethod.GET)
    public Treasure get(@PathVariable Long id){
        return treasureRepository.findOne(id);
    }

    @RequestMapping(value = "treasures/{id}", method = RequestMethod.PUT)
    public Treasure update(@PathVariable Long id, @RequestBody Treasure treasure){
        Treasure existingTreasure = treasureRepository.findOne(id);
        BeanUtils.copyProperties(treasure, existingTreasure);
        return treasureRepository.saveAndFlush(existingTreasure);
    }

    @RequestMapping(value = "treasures/{id}", method = RequestMethod.DELETE)
    public Treasure delete(@PathVariable Long id){
        Treasure existingTreasure = treasureRepository.findOne(id);
        treasureRepository.delete(existingTreasure);
        return existingTreasure;
    }

}
