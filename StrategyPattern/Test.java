
public class Test {

	public static void main(String[] args) {
		
		Warrior war = new Warrior();

		Knife kn = new Knife();
		Gun gn = new Gun();
		MachineGun mach = new MachineGun();
		Weapon weap = new Weapon();


		war.setWeapon(kn);
		war.useWeapon();

		war.setWeapon(new MachineGun());
		war.useWeapon();
		
		war.setWeapon(gn);
		war.useWeapon();
		
		war.setWeapon(mach);
		war.useWeapon();
		
		war.setWeapon(weap);
		war.useWeapon();
		
		Armor ar = new Armor();
		LightArmor lightAr = new LightArmor();
		HeavyArmor heavyAr = new HeavyArmor();
		
		war.setArmor(ar);
		war.wearArmor();
		
		war.setArmor(lightAr);
		war.wearArmor();
		
		war.setArmor(heavyAr);
		war.wearArmor();
		
	}

}
