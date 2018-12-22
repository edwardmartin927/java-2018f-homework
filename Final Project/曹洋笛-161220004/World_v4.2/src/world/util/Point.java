package world.util;

import java.util.Objects;

import world.Global;

/**
 *	�����ж�λ�ĵ�����
 *	
 *	@author Mirror
 *
 *	@see #pRow
 *	@see #pCol
 *	@see #row()
 *	@see #col()
 *	@see #equals(Object)
 *	@see #hashCode()
 *	@see #inWorld()
 *	@see #reset()
 *	@see #copy()
 *	@see #reverse()
 *	@see #mov(Point)
 *	@see #distance(Point)
	*/
public final class Point {

	/**	�ж�λ	*/
	private int pRow;
	/**	�ж�λ	*/
	private int pCol;

	/**	���캯��	*/
	public Point(int r, int c) {
		pRow = r;
		pCol = c;
	}
	
	/**	�õ�����������	*/
	public int row() {
		return pRow;
	}

	/**	�õ�����������	*/
	public int col() {
		return pCol;
	}

	/**	��д Object �� {@code Object.equals(key, k)} ����.
	 *	���� {@code Formation.formMap} Ѱ�Ҽ�ֵ�� {@code formMap.containsKey(point)} ʹ�õ���
	 *	�� Object �� {@code Object.equals(key, k)} ����������ʹ����HashCode��
	 *	�����Ҫ��д�Ա�֤�Ƚϵ���ֵ���ǵ�ַ
	 *
	 *	@param obj ��˵�Ƚ�����λ�ñȽ�
	 *	@return boolean �Ƿ���� 	*/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof Point) {
			Point p = (Point) obj;
			return ((this.pRow == p.pRow) && (this.pCol == p.pCol));
		}
		return false;
	}
	
	/**	��д Object �� {@code Object.hashCode()} ����.
	 *	���� Map ���ͣ�HashMap������д equals ��ͬʱҲҪ��д hashCode��
	 *	��Ϊ hashCode ��ֵ�Ǹ��ݵ�ַ���ü���Ķ����Ǹ���ֵ	*/
	@Override
	public int hashCode() {
		return Objects.hash(pRow, pCol);
	}

	/**	@param pLU ĳ������Χ�����Ͻ���������
	 *	@param pRD ĳ������Χ�����½���������
	 *	@return boolean �˵��Ƿ��ڸó�����Χ��	*/
	public boolean in(Point pLU, Point pRD) {
		return ((pRow >= pLU.row()) && (pRow <= pRD.row()) && (pCol >= pLU.col()) && (pCol <= pRD.col()));
	}

	/**	�˵��Ƿ������緶Χ��	*/
	public boolean inWorld() {
		return in(new Point(0, 0), new Point(Global.rowNum - 1, Global.colNum - 1));
	}
	
	/**	Ϊ�˵�������������Ϊ��p	*/
	public void reset(Point p) {
		pRow = p.pRow;
		pCol = p.pCol;
	}

	/**	Ϊ�˵�������������Ϊ(r, c)	*/
	public void reset(int r, int c) {
		pRow = r;
		pCol = c;
	}
	
	/**	�õ�һ�����Ƶĵ�	*/
	public Point copy() {
		return new Point(pRow, pCol);
	}

	/**	�õ�һ������ĵ�	*/
	public Point reverse() {
		return new Point(-pRow, -pCol);
	}
	
	/**	�õ�����p������в�֮��	*/
	public int distance(Point p) {
		return (Math.abs(pRow - p.pRow) + Math.abs(pCol - p.pCol));
	}
	
	/**	�����·����ƶ��˵㣬���λ��(d.row(), d.col())	*/
	public Point mov(Point d) {
		if (d == null) return this.copy();
		return new Point(pRow + d.pRow, pCol + d.pCol);
	}
	
	/**	��ӡ�˵�	*/
	public String show() {
		return ("(" + pRow + "," + pCol + ")");
	}
}
