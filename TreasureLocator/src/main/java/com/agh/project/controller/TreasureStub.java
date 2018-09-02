//Created by Malgorzata Sliwa 2018

package com.agh.project.controller;

import com.agh.project.model.Treasure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//class used for @RequestMapping before adding real database
public class TreasureStub {
	private static Map<Long, Treasure> treasures = new HashMap<Long, Treasure>();
	private static Long idIndex = 3L;

	//populate initial treasures
	static {
		Treasure a = new Treasure(1L, "Gold Mask", "Mask with gems", "POOR", 15, 22.12, 44.44, 2012);
		treasures.put(1L, a);
		Treasure b = new Treasure(2L, "Gold braceles", "Polish wedding braceles", "BAD", 10, 11.12, 22.44, 2013);
		treasures.put(2L, b);
		Treasure c = new Treasure(3L, "Gold Nackles", "Iron and Silver", "BAD", 12, 11.12, 22.44, 2014);
		treasures.put(3L, c);
	}

	public static List<Treasure> list() {
		return new ArrayList<Treasure>(treasures.values());
	}

	public static Treasure create(Treasure treasure) {
		idIndex += idIndex;
		treasure.setId(idIndex);
		treasures.put(idIndex, treasure);
		return treasure;
	}

	public static Treasure get(Long id) {
		return treasures.get(id);
	}

	public static Treasure update(Long id, Treasure treasure) {
		treasures.put(id, treasure);
		return treasure;
	}

	public static Treasure delete(Long id) {
		return treasures.remove(id);
	}
}
