package confrontation;
import java.util.ArrayList;

class Sneak extends Organisms{
	public Sneak() {
		super("  �߾�   ");
		this.type=3;
	}
	public void cheerUp() {
		
	}
}
class Sorpio extends Organisms{
	public Sorpio() {
		super(" Ы�Ӿ� ");
		this.type=4;
	}

}
class Lackeys extends Organisms{
	public Lackeys() {
		super(" Сආ� ");
		this.type=5;
	}
}
public class Bad {
	Sneak sneak;
	Sorpio sorpio;
	ArrayList<Lackeys> LackeysList;
	Formation badFormation;
	Bad(){
		sneak=new Sneak();
		sorpio=new Sorpio();
		LackeysList=new ArrayList<Lackeys>();
	}
	public void selectFormation(Battlefield battle) {
		//��һ��ѡ��
		badFormation=new FengShi();
		badFormation.arrange(this, battle);
	}
}
