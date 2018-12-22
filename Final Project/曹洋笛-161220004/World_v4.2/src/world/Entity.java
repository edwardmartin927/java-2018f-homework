package world;

import java.util.Random;

import world.creatures.Creature;
import world.util.*;

/**
 *	����ʵ���࣬�����ÿ�����ﶼ�����Ӧ��ʵ��. <br>
 *	������
 *	(1) �̶����ԣ�id + ���� + ״̬ + λ�� + �̣߳���Ϊ��������û�ж�������ʾ
 *		@see #id
 *		@see #state
 *		@see #creature
 *		@see #position
 *		@see #thread
 *
 * 	(2) �˶����ԣ��ƶ����� + Ŀ�����ָ�� + ʤ����������ж�������ʾ
 *		@see #direction
 *		@see #enemy
 *		@see #hasWined
 *
 * 	@see #setMoveDirection
 *	@see #run()
 *	@see #start()
 *	@see CharWindow.#entities
 * 
 *	@author Mirror
 */
public class Entity implements Runnable {
	
	/**	��ţ�Ϊ��Ӫ��*1000+����ƫ��	*/
	public int id;
	/**	״̬	*/
	public EntityState state;
	/**	����	*/
	public Creature creature;
	/**	λ��	*/
	public Point position;
	/**	�߳�	*/
	private Thread thread; 
	
	/**	�ƶ�����	*/
	public Point direction = new Point(0, 0);
	/**	�з������ָ���null	*/
	public Entity enemy = null;
	/**	��һ�غ�ʤ�����	*/
	public boolean hasWined = false;

	/**	�趨idֵ
	 *	@param bia ������λ�ã�����ƫ��	*/
	public void setId(Point bia) {
		if (creature.getGroup() == GroupType.Bro)
			id = 1000;
		else id = 2000;
		// ƫ��(û����������������ֻҪ��֤���ظ�����)
		id += bia.row() * Global.colNum + bia.col();
	}
	
	/**	��ʼ���캯��	*/
	public Entity(Point bia, Creature c, Point p) {
		state = EntityState.LIVE;
		creature = c.copy();
		position = p.copy();
		setId(bia);
	}
	/**	���캯��������xml�ļ�����	*/
	public Entity(int i, CreatureType ct, int orgr, int orgc) {
		id = i;
		creature = ct.getCreature();
		position = new Point(orgr, orgc);
		state = EntityState.LIVE;
	}

	/**	��������ֵ������xml�ļ�����	*/
	public void resetEntity(EntityState st, int pr, int pc, int movr, int movc, Entity ene, boolean w) {
		state = st;
		position.reset(pr, pc);
		direction.reset(movr, movc);
		enemy = ene;
		hasWined = w;
	}
	
	/**	����[min, max]���������	*/
	private int getRandom(int min, int max) {
		Random random = new Random();
		return (random.nextInt(max - min + 1) + min);
	}
	
	/**	������ʤ��	*/
	private boolean win(GroupType type) {
		int bias = getRandom(-10, 10);
		if (type == GroupType.Bro)
			return (getRandom(0, 99) < Global.BroWinPercent + bias);
		else
			return (getRandom(0, 99) < Global.MonWinPercent - bias);
	}
	
	/**	��ȡ����ĵз�ʵ���λ��
	 *	@return ���з�ȫ�����𣬷���null�����򷵻ؾ�������ĵз�ʵ��λ��	*/
	private Point getNearestEnemy() {
		Entity nearEn = null; // ����ĵз�ʵ��ָ��
		int nearD = Integer.MAX_VALUE; // �������
		for (Entity en: CoreWorld.entities.values()) {
			if ((en.state == EntityState.LIVE) && (en.creature.getGroup() != creature.getGroup())) {
				int tmpD = en.position.distance(position);
				if (tmpD < nearD) {
					nearD = tmpD;
					nearEn = en;
				}
			}
		}
		return ((nearEn == null) ? (null) : (nearEn.position));
	}
	
	/**	�ж�ĳλ���Ƿ���ʵ��
	 *	@return ���򷵻ظ�ʵ��Entity��ָ�룬û���򷵻�null	*/
	private Entity getLiveEntityAt(Point pos) {
		for (Entity en: CoreWorld.entities.values()) {
			if ((en.position.equals(pos)) && (en.state == EntityState.LIVE))
				return en;
		}
		return null;
	}

	/**	��ʼ���˶�����	*/
	public void stand() {
		direction.reset(0, 0); 
		enemy = null;
		hasWined = false;
	}
	
	/**	��ȡ����з����ʵ����������ֻ�����»�����.
	 *	����directionΪ(1, 0) �� (-1, 0) �� (0, 1) �� (0, -1)��(0, 0) 
	 *	������ͬʱ�趨����ʵ�����ķ���	*/
	private void setMoveDirection() {
		stand(); // ��ʼ���˶�����
		// ��Ҫ�󱣳�����
		if (Global.keepFormationRound > 0) { 
			if (creature.getGroup() == GroupType.Bro) 
				direction.reset(0, 1);
			else 
				direction.reset(0, -1);
		} 
		else { // ���������Ҷ�ģʽ
			Point pTarget = getNearestEnemy();
			if (pTarget == null) { // ����ȫ������ս������
				Global.battleEnd = true;
				return;
			}
			int signRow = (pTarget.row() > position.row()) ? (1) : (-1);
			int signCol = (pTarget.col() > position.col()) ? (1) : (-1);
			// ��ǿ����ԣ�ʹ��ս������
			int rand = getRandom(1, 10);
			if (pTarget.row() == position.row()) 
				direction.reset((rand <= 2) ? (new Point(signRow, 0)) : (new Point(0, signCol)));
			else if (pTarget.col() == position.col())
				direction.reset((rand <= 8) ? (new Point(signRow, 0)) : (new Point(0, signCol)));
			else 
				direction.reset((rand <= 5) ? (new Point(signRow, 0)) : (new Point(0, signCol)));
		}
		// �鿴Ŀ��λ�����
		enemy = getLiveEntityAt(position.mov(direction));
		if (enemy != null && enemy.creature.getGroup() == creature.getGroup()) { 
			// Ŀ��λ����ͬ��Ӫʵ��
			enemy = null;
			if (!(Global.keepFormationRound > 0)) // ���Ҷ�ʱ�����ƶ�
				direction.reset(0, 0); 
		}
		// Ŀ��λ���ǿյػ���ˣ��ƶ��ճ�
		return;
	}
	
	/**	�������ƶ�Entity
	 *	����Entity�������ʵ���ķ���	*/
	@Override
	public void run() {
		synchronized (this) {
			if (Global.battleEnd == true) return; // ս������
			if (state != EntityState.LIVE) return; // ��ʵ���������/����
			// �����ƶ�
			setMoveDirection(); 
			if (direction.equals(new Point(0, 0))) return;// û���ƶ�
			// ���ƶ�
			position.reset(position.mov(direction));
			// ���������
			if (!position.inWorld())
				state = EntityState.OUT;
			// ��������˵���
			if (enemy != null) { 
				hasWined = win(creature.getGroup()); // ������
				if (hasWined) 
					enemy.state = EntityState.DEAD;
				else this.state = EntityState.DEAD;
			}
		}
	}
	
	/**	�����߳�	*/
	public void start() {
    	thread = new Thread(this);
    	thread.start();
	}
}
