package world.formations;

import world.creatures.*;
import world.util.*;

/**
 *	 ������row*col = 8 * 2. <br>
 *	
 *	 		0	1		0	1 <br>
 *	 
 *	0		O	&		#	O <br>
 *	1		&	O		O	# <br>
 *	2		O	&		#	O <br>
 *	3		&	O		O	# <br>
 *	4		O	&		#	O <br>
 *	5		&	O		O	# <br>
 *	6		O	&		#	O <br>
 *	7		&	O		O	# <br>
 *	
 *	@author Mirror
 */
public final class ChongE extends Formation {
	
	public ChongE(GroupType ct) {		
		super(FormationType.CE, 8, 2, 3, 0); // ����
		if (ct == GroupType.Bro) {
			// ��«�޵�λ��
			formMap.put(new Point(0, 0), new Brothers(0));
			formMap.put(new Point(2, 0), new Brothers(1));
			formMap.put(new Point(4, 0), new Brothers(2));
			formMap.put(new Point(6, 0), new Brothers(3));
			formMap.put(new Point(1, 1), new Brothers(4));
			formMap.put(new Point(3, 1), new Brothers(5));
			formMap.put(new Point(5, 1), new Brothers(6));
			formMap.put(new Point(7, 1), new Elder()); // ��үү
		}
		else {
			// ���ֵ�λ��
			formMap.put(new Point(1, 0), new Monsters());
			formMap.put(new Point(3, 0), new Monsters());
			formMap.put(new Point(5, 0), new Monsters());
			formMap.put(new Point(7, 0), new Monsters());
			formMap.put(new Point(0, 1), new Monsters());
			formMap.put(new Point(2, 1), new Scorpion()); // Ы�Ӿ�
			formMap.put(new Point(4, 1), new Snake()); // �߾�
			formMap.put(new Point(6, 1), new Monsters());
		}
	}
}
