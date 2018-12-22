package world.formations;

import java.util.HashMap;
import java.util.Map;

import world.creatures.Creature;
import world.util.*;

/**
 *	���ͳ�����.
 *	���������������� 0 ��ʼ
 *
 *	@author Mirror
 *	
 *	@see #type
 *	@see #formMap
 *	@see #getCreature(Point)
 */
public abstract class Formation {

	/**	�������� {@value}	*/
	private FormationType type;
	
	/**	������ռ���� {@value}	*/
	private int formRowNum;
	
	/**	������ռ���� {@value}	*/
	private int formColNum;
	
	/**	�������� {@value}	*/
	private Point pFormCen;
	
	/**	�����������(����, ʵ��)�Լ��� {@value}	*/
	public Map<Point, Creature> formMap;
	
	/**	���캯�����õ�����ֵ	*/
	protected Formation(FormationType t, int r, int c, int cr, int cc) {
		type = t;
		formRowNum = r;
		formColNum = c;
		pFormCen = new Point(cr, cc);
		formMap = new HashMap<Point, Creature>();
	}

	/**	���캯�������	*/
	protected Formation(FormationType t) {
		type = t;
		formMap = new HashMap<Point, Creature>();
	}

	/**	��������ֵ	*/
	protected void init(int r, int c, int cr, int cc) {
		formRowNum = r;
		formColNum = c;
		pFormCen = new Point(cr, cc);
	}
	
	/**	�õ���������	*/
	public FormationType getType() {
		return type;
	}
	
	/**	�õ���������	*/
	public int getRowNum() {
		return formRowNum;
	}
	
	/**	�õ���������	*/
	public int getColNum() { 
		return formColNum;
	}

	/**	�õ���������	*/
	public Point getFormCen() {
		return pFormCen;
	}
	
	/**	�õ������ڵ�p���Ķ���	*/
	public Creature getCreature(Point p) {
		return formMap.get(p);
	}
}
