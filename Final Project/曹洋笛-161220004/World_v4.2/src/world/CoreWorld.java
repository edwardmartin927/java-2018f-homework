package world;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import world.formations.*;
import world.gui.GUIWindow;
import world.util.*;
import world.xml.*;

/**
 *	�������磬����Ļ���. <br>
 *	�����ն���ʾ��������
 *
 *	@author Mirror
 *
 *	@see #broForm
 *	@see #monForm
 *	@see #entities
 *	@see #initAll()
 *	@see #initFormation(FormationType, GroupType)
 *	@see #showWorld()
 *	@see GUIWindow
 */
public final class CoreWorld {

	/**	�����«�����Ͷ��󣬽������ڶ�ʵ��ĳ�ʼ��	*/
	public Formation broForm;
	/**	�����������Ͷ��󣬽������ڶ�ʵ��ĳ�ʼ��	*/
	public Formation monForm;

	/**	ʵ�壬������+λ��+�̣߳�һ����Ϊ�ķ�����	*/
	public static Map<Integer, Entity> entities = new HashMap<>();

	/**	���캯������ʼ�����ж�����λ��	*/
	public CoreWorld() {
		initAll();
	}
	
	/**	��ʼ��ȫ�ֱ���	*/
	public void initGlobal(boolean playingBack) {
		Global.keepFormationRound = Global.keepFormationRoundNum;
		Global.roundNum = 0;
		// battleStart/battleEnd�ı仯����Ϊ��
		// [׼��]��battleStart = false, battleEnd = false
		// [������]��battleStart = true, battleEnd = false
		// [����]��battleStart = true, battleEnd = true
		Global.battleStart = false;
		Global.battleEnd = false;
		Global.battlePlayingBack = playingBack;
		Global.recordWriter = new XMLRecordWriter();
	}
	
	/**	���ݵ�ǰ��������д���ʼ����ʵ��	*/
	private void initEntities() {
		// ���Ƴ���ǰ���͵�ȫ��ʵ��
		Iterator<Integer> it = entities.keySet().iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        // �����µ�ʵ�����
		for (Point p : broForm.formMap.keySet()) { // ����ȫ����«��
			Point pos = Global.leftCenterP.mov(broForm.getFormCen().reverse()).mov(p);
			Entity en = new Entity(p, broForm.getCreature(p), pos);
			entities.put(en.id, en);
		}
		for (Point p : monForm.formMap.keySet()) { // ����ȫ������
			Point pos = Global.rightCenterP.mov(monForm.getFormCen().reverse()).mov(p);
			Entity en = new Entity(p, monForm.getCreature(p), pos);
			entities.put(en.id, en);
		}
		// ��ʱ�洢
		Global.recordWriter.addEntityElements();
		System.out.println("��ʼ���� �� �� �� �� �� �� ���");
		System.out.println("׼��ս��");
	}
	
	/**	��ʼ��	*/
	public void initAll() {
		broForm = new ChangShe(GroupType.Bro); // ��«�����ͣ���ʼ��Ϊ������
		monForm = new HeYi(GroupType.Mon); // �������ͣ���ʼ��Ϊ������
		// ������������ʵ�弯
		initGlobal(false);
		initEntities();
	}
	
	/**	�ı����͵�ͬʱ���ã�λ�ó�ʼ��ΪĬ��λ��	*/
	public void initFormation(FormationType ftype, GroupType gtype) {
		if (gtype == GroupType.Bro) 
			broForm = ftype.getFormation(GroupType.Bro);
		else 
			monForm = ftype.getFormation(GroupType.Mon);
		initGlobal(false);
		initEntities();
	}
	
	/**	��ʾ��������	*/
	public void showWorld() {
		 // ��ʼ����ʾ�ĵ�ͼ
		char[][] cMap = new char[Global.rowNum][Global.colNum];
		for (int i = 0; i < Global.rowNum; i++) { 
			for (int j = 0; j < Global.colNum; j++)
				cMap[i][j] = ' '; 
		}
		// ��������ʵ��
		for (Entity en: entities.values()) {
			if (en.state == EntityState.LIVE) {
				cMap[en.position.row()][en.position.col()] = en.creature.getSymbol();
			}
		}
		// ��ʼ��ӡ��ͼ
		char boundary = '#';
		// ��һ�У���ͼ�߽�
		for (int j = 0; j < Global.colNum + 2; j++)
			System.out.print(boundary + " ");
		System.out.println();
		// ��ͼ����
		for (int i = 0; i < Global.rowNum; i++) {
			System.out.print(boundary);
			for (int j = 0; j < Global.colNum; j++)
				System.out.print(" " + cMap[i][j]);
			System.out.println(" " + boundary);
		}
		// ���һ�У���ͼ�߽�
		for (int j = 0; j < Global.colNum + 2; j++)
			System.out.print(boundary + " ");
		System.out.println();
	}
}
