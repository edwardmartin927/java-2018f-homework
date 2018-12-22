package world.xml;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import world.*;
import world.util.*;

/**
 *	ʵ��ս����¼xml�ļ��ĵ���
 *
 *	@author Mirror
 *
 *	@see #XMLRecordReader(String)
 *	@see #initEntitiesByElement()
 *	@see #setEntityByRound(Entity, int)
 */
public class XMLRecordReader extends XMLRecordStructure {

	/**	�ĵ�	*/
	private Document doc = null;
	/**	��Ԫ��	*/
	private Element root = null;
	
	/**	���캯�������ļ�filename��ȡ	*/
	public XMLRecordReader(String filename) {
		super();
		try {
			// ʹ��SAXReader��ȡ�ĵ�
			SAXReader reader = new SAXReader();
			doc = reader.read(new File(filename));
            System.out.println("��ȡ��ս�����ԡ�" + filename + "��");
			// ��ȡ��Ԫ��
			root = doc.getRootElement();
			// ��ʼ��entities����
			initEntitiesByElement();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	/**	��entitiesд��
	 *	��root�н���������ʵ������ʼֵ������0�غϣ������浽entities����	*/
	private void initEntitiesByElement() {
		// ���Ƴ���ǰentities���ϵ�ȫ��ʵ��
		Iterator<Integer> it = CoreWorld.entities.keySet().iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        // �����ȡ��Entity����
		List<Element> entityEleList = root.elements();
		for (Element entityEle: entityEleList) {
			String istr = entityEle.getName();
			// ��ȡentityEle��name�ĺ�4λ
			int i = Integer.parseInt(istr.substring(istr.length() - 4, istr.length()));
			CreatureType ct = toCreatureType(entityEle.attributeValue(ctype));
			int orgr = Integer.parseInt(entityEle.attributeValue(r));
			int orgc = Integer.parseInt(entityEle.attributeValue(c));
			Entity en = new Entity(i, ct, orgr, orgc);
			CoreWorld.entities.put(en.id, en);
		}
		System.out.println("���ݶ�ȡ�� �� �� �� �� �� �� ���");
		System.out.println("׼���ط�");
	}
	
	/**	��entityд��
	 *	��root�н�����ʵ�����entity�ĵ�n�غϵ�ֵ������	*/
	public void setEntityByRound(Entity en, int n) {
		// ʹ�ô˺�����ȷ��entities���ϲ�Ϊ��
		Element roundEle = root.element(entity + en.id).element(round + n);
		if (roundEle != null) {
			EntityState st = toEntityState(roundEle.attributeValue(state));
			int pr = Integer.parseInt(roundEle.attributeValue(posr));
			int pc = Integer.parseInt(roundEle.attributeValue(posc));
			int movr = Integer.parseInt(roundEle.attributeValue(dr));
			int movc = Integer.parseInt(roundEle.attributeValue(dc));
			Entity ene = toEnemy(roundEle.attributeValue(enemy));
			boolean w = toBoolean(roundEle.attributeValue(win));
			en.resetEntity(st, pr, pc, movr, movc, ene, w);
			// �ж�ս���Ƿ����
			if (root.element(entity + en.id).element(round + (n + 1)) == null)
				Global.battleEnd = true;
		}
	}
}
