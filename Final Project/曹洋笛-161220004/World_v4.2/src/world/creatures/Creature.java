package world.creatures;

import world.util.*;

/**	
 *	������
 *	
 *	@author Mirror
 *	
 *	@see #type
 *	@see #getGroup()
 */
public class Creature {

	/**	��������	*/
	protected CreatureType type;

	/**	��������	*/
    protected String name; 

	/**	�������	*/
    protected char symbol;

    protected Creature() {}
    private Creature(CreatureType t, String n, char s) {
    	type = t;
    	name = n;
    	symbol = s;
    }

	/**	�õ���������	*/
    public CreatureType getType() {
    	return type;
    }

	/**	�õ�������Ӫ	*/
    public GroupType getGroup() {
    	return type.group();
    }

	/**	�õ���������	*/
    public String getName() {
        return name;
    }

	/**	�õ��������	*/
    public char getSymbol() {
        return symbol;
    }
    
    /**	���ƣ����ڸ�ֵ	*/
    public Creature copy() {
    	return new Creature(type, name, symbol);
    }
    
    @Override
    public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof Creature) {
			Creature c = (Creature) obj;
			return (this.type == c.type);
		}
		return false;
    }
}
