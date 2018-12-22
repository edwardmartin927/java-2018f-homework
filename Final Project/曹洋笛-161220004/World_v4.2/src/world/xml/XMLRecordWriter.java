package world.xml;

import java.io.FileOutputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import world.*;

/**
 *	ʵ��ս����¼xml�ļ��ĵ���
 *
 *	@author Mirror
 *
 *	@see #XMLRecordWriter()
 *	@see #addEntityElements()
 *	@see #addRoundElements(int)
 */
public class XMLRecordWriter extends XMLRecordStructure {
	
	/**	�ĵ�	*/
	private Document doc = null;
	/**	��Ԫ��	*/
	private Element root = null;
	
	public XMLRecordWriter() {
		super();
		// ����һ��Document�����ʾһ�����ĵ�
		doc = DocumentHelper.createDocument();
		if (root != null)
			removeAll();
		root = doc.addElement(records);
	}

	/**	ɾ�����ڵ��������нڵ�	*/
	private void removeAll() {
		List<Element> entityEleList = root.elements();
		for (Element entityEle: entityEleList) {
			root.remove(entityEle);
		}
	}
	
	/**	��entities����
	 *	��ʱ�洢entities���ϳ�ʼֵ������0�غϣ�	*/
	public void addEntityElements() {
		// �������ʱ�洢��
		removeAll();
		// ����entities���洢
		for (Entity en: CoreWorld.entities.values()) {
			Element entityEle = root.addElement(entity + en.id);
			entityEle.addAttribute(ctype, en.creature.getType().label);
			entityEle.addAttribute(r, String.valueOf(en.position.row()));
			entityEle.addAttribute(c, String.valueOf(en.position.col()));
		}
	}
	
	/**	��entities����
	 *	��ʱ�洢��n�غϽ�����entities���ϵ�ֵ	*/
	public void addRoundElements(int n) {
		// ����entities���洢
		for (Entity en: CoreWorld.entities.values()) {
			Element roundEle = root.element(entity + en.id).addElement(round + n);
			roundEle.addAttribute(state, en.state.label);
			roundEle.addAttribute(posr, String.valueOf(en.position.row()));
			roundEle.addAttribute(posc, String.valueOf(en.position.col()));
			roundEle.addAttribute(dr, String.valueOf(en.direction.row()));
			roundEle.addAttribute(dc, String.valueOf(en.direction.col()));
			roundEle.addAttribute(enemy, toEntityLabel(en.enemy));
			roundEle.addAttribute(win, toBooleanLabel(en.hasWined));
		}
	}
	
	/**	�������ļ�filename	*/
	public void saveRecord(String filename) {
		try{
			// ����XmlWriter����
            XMLWriter writer = new XMLWriter(OutputFormat.createPrettyPrint());
            FileOutputStream fos = new FileOutputStream(filename);
            writer.setOutputStream(fos);
            // ����
            writer.write(doc);
            System.out.println("��ս���̱�������" + filename + "��");
            writer.close();
        } catch(Exception e){
            e.printStackTrace();
        }
	}
}
