package confrontation;

class CalabashGuy extends Organisms{
	public String color;
	private int rank;
	public CalabashGuy(String name,String color,int rank) {
		this.name=name;
		this.color=color;
		this.rank=rank;
		this.type=1;
	}
	public void skill() {
		switch(rank) {
		
		}
	}
}
class CalabashBrothers{
	private int num=7;
	CalabashGuy[] calabashBrothers=new CalabashGuy[7];
	public CalabashBrothers() {
		calabashBrothers[0]=new CalabashGuy("�ϴ�","��ɫ",1);
		calabashBrothers[1]=new CalabashGuy("�϶�","��ɫ",2);
		calabashBrothers[2]=new CalabashGuy("����","��ɫ",3);
		calabashBrothers[3]=new CalabashGuy("����","��ɫ",4);
		calabashBrothers[4]=new CalabashGuy("����","��ɫ",5);
		calabashBrothers[5]=new CalabashGuy("����","��ɫ",6);
		calabashBrothers[6]=new CalabashGuy("����","��ɫ",7);
	}
	public int getNum() {
		return num;
	}
}
class Grandpa extends Organisms{
	public Grandpa() {
		super("үү");
		this.type=2;
	}
	public void cheerUp() {
		
	}
}
public class Good {
	Grandpa grandpa;
	CalabashBrothers calabash;
	Formation goodFormation;
	public Good() {
		grandpa=new Grandpa();
		calabash=new CalabashBrothers();
	}
	public void selectFormation(Battlefield battle) {
		//����Ӧ����һ��ѡ��
		goodFormation=new ChangShe();
		goodFormation.arrange(this, battle);
	}
}
