package world.util;

import world.creatures.*;

/**	
 *	�������ϸ��𻮷�. <br>
 *	ֻҪ���ڲ�ͬ��Ϊ��ͬ�����࣬���в�ͬ��enum
 *
 *	@author Mirror
 */
public enum CreatureType {
	Bro1("Bro1", "�ϴ�", "bro1"),
	Bro2("Bro2", "�϶�", "bro2"),
	Bro3("Bro3", "����", "bro3"),
	Bro4("Bro4", "����", "bro4"),
	Bro5("Bro5", "����", "bro5"),
	Bro6("Bro6", "����", "bro6"),
	Bro7("Bro7", "����", "bro7"),
	Scorp("Scorp", "Ы�Ӿ�", "scorp"),
	Mons("Mons", "Сආ�", "mons"),
	Snk("Snk", "�߾�", "snk"),
	Eld("Eld", "��үү", "eld");
	
	public String label; // xml��ǩ
	public String detail; // ϸ�������
	public String imgName; // ��ӦͼƬ��
	
	/**	enum �Ĺ��캯��
	 *	@param rou ���������
	 *	@param det ϸ�������
	 *	@param nam ��ӦͼƬ�� */
	private CreatureType(String lab, String det, String nam) {
		this.label = lab;
		this.detail = det;
		this.imgName = nam;
	}
	
	/**	@return ��Ӧ����Ӫ */
	public GroupType group() {
		switch (this) {
		case Mons: case Scorp: case Snk: return GroupType.Mon;
		default: return GroupType.Bro;
		}
	}
	
	/**	@return ��Ӧ��������� */
	public Creature getCreature() {
		switch (this) {
		case Bro1: return new Brothers(0);
		case Bro2: return new Brothers(1);
		case Bro3: return new Brothers(2);
		case Bro4: return new Brothers(3);
		case Bro5: return new Brothers(4);
		case Bro6: return new Brothers(5);
		case Bro7: return new Brothers(6);
		case Scorp: return new Scorpion();
		case Mons: return new Monsters();
		case Snk: return new Snake();
		case Eld: return new Elder();
		default: return null;
		}
	}
}
