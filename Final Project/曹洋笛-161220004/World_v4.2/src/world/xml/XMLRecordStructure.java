package world.xml;

import world.*;
import world.util.*;

/**
 *	���ڻ���xml�ṹ���ļ�����.<br>
 *	�ṹ���磺<br>
 *	<records>
 *		<entity1000 ctype="Bro" r="10" c="4" />
 *			<round1 state="LIVE" posr="12" posc="3" dr="0" dc="1" enemy="null" win="false" />
 *			<round2 ... />
 *			<round3 ... />
 *		<entity1017 ... />
 *		<entity1034 ... />
 *	</records>
 *
 *	@author Mirror
 */
public class XMLRecordStructure {

	// ����Ϊ���ڵ���String
	protected static final String records = "records"; // ��
	protected static final String entity = "entity"; // ��һ���ӽڵ�
	protected static final String ctype = "ctype"; // Entity -> creature -> type
	protected static final String r = "r"; // Entity��ʼλ��
	protected static final String c = "c"; // Entity��ʼλ��
	protected static final String round = "round"; // �ڶ����ӽڵ�
	protected static final String state = "state"; // Entity -> state
	protected static final String posr = "posr"; // Entity -> position -> row
	protected static final String posc = "posc"; // Entity -> position -> col
	protected static final String dr = "dr"; // Entity -> direction -> row
	protected static final String dc = "dc"; // Entity -> direction -> col
	protected static final String enemy = "enemy"; // Entity -> enemy
	protected static final String win = "win"; // Entity -> hasWined
	
	/**	@return ��Ӧ�ı�ǩ�ַ��� */
	protected static String toBooleanLabel(boolean bool) {
		return ((bool) ? ("true") : ("false"));
	}

	/**	@return ��Ӧ��booleanֵ */
	protected static boolean toBoolean(String label) {
		return ((label == "true") ? (true) : (false));
	}

	/**	@return ��Ӧ���������� */
	protected static CreatureType toCreatureType(String label) {
		switch (label) {
		case "Bro1": return CreatureType.Bro1;
		case "Bro2": return CreatureType.Bro2;
		case "Bro3": return CreatureType.Bro3;
		case "Bro4": return CreatureType.Bro4;
		case "Bro5": return CreatureType.Bro5;
		case "Bro6": return CreatureType.Bro6;
		case "Bro7": return CreatureType.Bro7;
		case "Scorp": return CreatureType.Scorp;
		case "Mons": return CreatureType.Mons;
		case "Snk": return CreatureType.Snk;
		case "Eld": return CreatureType.Eld;
		default: return null;
		}
	}

	/**	@return ��Ӧ��״ֵ̬ */
	protected static EntityState toEntityState(String label) {
		switch (label) {
		case "LIVE": return EntityState.LIVE;
		case "DEAD": return EntityState.DEAD;
		default: return EntityState.OUT;
		}
	}
	
	/**	@return ��Ӧ�ĵз�ʵ�����ָ��	*/
	protected static Entity toEnemy(String label) {
		if (label.equals("null")) 
			return null;
		else // labelΪentities���ϵ�id��
			return CoreWorld.entities.get(Integer.parseInt(label));
	}

	/**	@return ��Ӧ�ĵз������ַ��� */
	protected static String toEntityLabel(Entity ene) {
		if (ene == null)
			return "null";
		else
			return String.valueOf(ene.id);
	}

}
