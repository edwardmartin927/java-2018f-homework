package world.formations;

import world.creatures.*;
import world.util.*;

/**
 *	 ������row*col = 8 * 8. <br>
 *	
 *	 		0	1	2	3	4	5	6	7		0	1	2	3	4	5	6	7 <br>
 *	 
 *	0		O	O	O	O	O	O	O	&		#	O	O	O	O	O	O	O <br>
 *	1		O	O	O	O	O	O	&	O		O	#	O	O	O	O	O	O <br>
 *	2		O	O	O	O	O	&	O	O		O	O	#	O	O	O	O	O <br>
 *	3		O	O	O	O	&	O	O	O		O	O	O	#	O	O	O	O <br>
 *	4		O	O	O	&	O	O	O	O		O	O	O	O	#	O	O	O <br>
 *	5		O	O	&	O	O	O	O	O		O	O	O	O	O	#	O	O <br>
 *	6		O	&	O	O	O	O	O	O		O	O	O	O	O	O	#	O <br>
 *	7		&	O	O	O	O	O	O	O		O	O	O	O	O	O	O	# <br>
 *	
 *	@author Mirror
 */
public final class YanXing extends Formation {
	
	public YanXing(GroupType ct) {		
		super(FormationType.YX); // ����
		if (ct == GroupType.Bro) {
			init(8, 8, 3, 3); // ����ͼ��ռ����
			// ��«�޵�λ��
			formMap.put(new Point(0, 0), new Brothers(0));
			formMap.put(new Point(1, 1), new Brothers(1));
			formMap.put(new Point(2, 2), new Brothers(2));
			formMap.put(new Point(3, 3), new Brothers(3));
			formMap.put(new Point(4, 4), new Brothers(4));
			formMap.put(new Point(5, 5), new Brothers(5));
			formMap.put(new Point(6, 6), new Brothers(6));
			formMap.put(new Point(7, 7), new Elder()); // ��үү
		}
		else {
			init(8, 8, 3, 4); // ����ͼ��ռ����
			// ���ֵ�λ��
			formMap.put(new Point(0, 7), new Monsters());
			formMap.put(new Point(1, 6), new Monsters());
			formMap.put(new Point(2, 5), new Scorpion()); // Ы�Ӿ�
			formMap.put(new Point(3, 4), new Monsters());
			formMap.put(new Point(4, 3), new Monsters());
			formMap.put(new Point(5, 2), new Snake()); // �߾�
			formMap.put(new Point(6, 1), new Monsters());
			formMap.put(new Point(7, 0), new Monsters());
		}
	}
}
