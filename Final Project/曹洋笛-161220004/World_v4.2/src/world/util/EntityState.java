package world.util;

/**
 *	ʵ��״̬
 *
 *	@author Mirror
 */
public enum EntityState {
	LIVE("LIVE"), // ����
	DEAD("DEAD"), // ����
	OUT("OUT"); // �������緶Χ
	
	public String label; // xml��ǩ
	private EntityState(String lab) { // ���췽��
		this.label = lab;
	}
}
