package world.formations;

import world.creatures.*;
import world.util.*;

/**
 *	 ������row*col = 7 * 5 / 5 * 4. <br>
 *	
 *			0	1	2	3	4		0	1	2	3 <br>
 *	 
 *	0		O	O	O	&	O		O	#	O	O <br>
 *	1		O	O	&	O	O		O	#	O	O <br>
 *	2		O	&	O	&	O		#	#	O	# <br>
 *	3		&	O	&	O	&		O	#	#	O <br>
 *	4		O	O	O	&	O		O	#	O	O <br>
 *	5		O	O	&	O	O <br>
 *	6		O	O	O	&	O <br>
 *	
 *	@author Mirror
 */
public final class YuLin extends Formation {
	
	public YuLin(GroupType ct) {		
		super(FormationType.YL); // ����
		if (ct == GroupType.Bro) {
			init(5, 4, 2, 1); // ����ͼ��ռ����
			// ��«�޵�λ��
			formMap.put(new Point(2, 0), new Elder()); // ��үү
			formMap.put(new Point(0, 1), new Brothers(0));
			formMap.put(new Point(1, 1), new Brothers(1));
			formMap.put(new Point(2, 1), new Brothers(2));
			formMap.put(new Point(3, 1), new Brothers(3));
			formMap.put(new Point(4, 1), new Brothers(4));
			formMap.put(new Point(3, 2), new Brothers(5));
			formMap.put(new Point(2, 3), new Brothers(6));
		}
		else {
			init(7, 5, 3, 2); // ����ͼ��ռ����
			// ���ֵ�λ��
			formMap.put(new Point(0, 3), new Monsters());
			formMap.put(new Point(1, 2), new Monsters());
			formMap.put(new Point(2, 1), new Monsters());
			formMap.put(new Point(2, 3), new Monsters());
			formMap.put(new Point(3, 0), new Monsters());
			formMap.put(new Point(3, 2), new Scorpion()); // Ы�Ӿ�
			formMap.put(new Point(3, 4), new Snake()); // �߾�
			formMap.put(new Point(4, 3), new Monsters());
			formMap.put(new Point(5, 2), new Monsters());
			formMap.put(new Point(6, 3), new Monsters());
		}
	}
}
