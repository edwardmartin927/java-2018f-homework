package world.util;

import world.formations.ChangShe;
import world.formations.ChongE;
import world.formations.FangYuan;
import world.formations.FengShi;
import world.formations.Formation;
import world.formations.HeYi;
import world.formations.YanXing;
import world.formations.YanYue;
import world.formations.YuLin;

/**	
 *	�������ͣ���8�֣�
 * 
 *	@author Mirror
 */
public enum FormationType {
	
	HY("������"),
	YX("������"),
	CE("������"),
	CS("������"),
	YL("������"),
	FY("������"),
	YY("������"),
	FS("��ʸ��");
	
	public String name;
	private FormationType(String name) { // ���췽��
		this.name = name;
	}
	
	/**	@param type ��Ӫ
	 *	@return ��Ӧ�����Ͷ��� */
	public Formation getFormation(GroupType type) {
		switch (this) {
		case HY: return new HeYi(type); 
		case YX: return new YanXing(type); 
		case CE: return new ChongE(type);
		case CS: return new ChangShe(type);
		case YL: return new YuLin(type);
		case FY: return new FangYuan(type);
		case YY: return new YanYue(type);
		case FS: return new FengShi(type);
		default: return new ChangShe(type); 
		}
	}
}
