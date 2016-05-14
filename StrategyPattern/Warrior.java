
public class Warrior {
	Weapon weapon;
	Armor armor;
	
	void setWeapon(Weapon weap) {
		this.weapon = weap;
	}
	void useWeapon() {
		if(weapon!= null){
			weapon.use();			
		}else{
			System.out.println("No weapon!");			
		}
	}
	void setArmor(Armor ar) {
		this.armor = ar;
	}
	void wearArmor() {
		if(armor!= null){
			armor.wear();			
		}else{
			System.out.println("No armor!");			
		}
	}
	
}
