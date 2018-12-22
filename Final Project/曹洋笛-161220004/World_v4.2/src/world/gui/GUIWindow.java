package world.gui;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javafx.fxml.FXML;
import javafx.animation.*;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;

import world.*;
import world.util.*;
import world.xml.XMLRecordReader;

public class GUIWindow {

	/**	CoreWorldָ��	*/
	public CoreWorld cWrd = null;
	
	@FXML
	private Label background; // ����ͼƬ��ǩ
	
	@FXML
	public GridPane entityLayer; // �����ָ��
	
	@FXML
	public GridPane animLayer; // ������ָ��
	
	@FXML
	private Label modeLabel; // ģʽ��[ս��]��[�ط�]��״̬��[׼��]��[������]��[����]

	@FXML
	private Label groupLabel; // �ж�����[��«��]��[����]
	
	@FXML
	private Label roundLabel; // ��ʾ�غ���
	
	@FXML
	private Label observer; // ���ڴ�ӡս�����
	
	@FXML
	private TextField receiver; // �������ü��̼���
	
	/**	����Label��ά����	*/
	private Label cells[][] = new Label[Global.rowNum][Global.colNum];

	/**	����Label��	*/
	public static Map<Integer, Label> animItems = new HashMap<>();

	/**	��ʼ��CoreWorld	*/
    public void setWin(CoreWorld cw) {
    	cWrd = cw;
    }

	/**	��ʼ��ģʽ״̬/�غ�����ǩ	*/
    public void initLabels(boolean onlyText) {
    	if (Global.battlePlayingBack)
    		modeLabel.setText("�ط�׼��");
    	else modeLabel.setText("ս��׼��");
		roundLabel.setText("�غ�����0");
		groupLabel.setText("�ж�����---");
		observer.setVisible(false);
    	if (onlyText) return;
		modeLabel.setPrefWidth(Global.labelWidth(6));
		modeLabel.setPrefHeight(Global.labelHeight);
		modeLabel.setTranslateX(Global.marginLabelX(6));
		modeLabel.setTranslateY(Global.marginLabelY);
		modeLabel.setBackground(new Background(new BackgroundFill(new Color(1, 1, 1, 0.5), null, null)));
		roundLabel.setPrefWidth(Global.labelWidth(6));
		roundLabel.setPrefHeight(Global.labelHeight);
		roundLabel.setTranslateX(-Global.marginLabelX(6) - Global.labelWidth(8));
		roundLabel.setTranslateY(Global.marginLabelY);
		roundLabel.setBackground(new Background(new BackgroundFill(new Color(1, 1, 1, 0.5), null, null)));
		groupLabel.setPrefWidth(Global.labelWidth(8));
		groupLabel.setPrefHeight(Global.labelHeight);
		groupLabel.setTranslateX(-Global.marginLabelX(8));
		groupLabel.setTranslateY(Global.marginLabelY);
		groupLabel.setBackground(new Background(new BackgroundFill(new Color(1, 1, 1, 0.5), null, null)));
		observer.setPrefHeight(2 * Global.labelHeight);
		observer.setBackground(new Background(new BackgroundFill(new Color(1, 1, 1, 0.5), null, null)));
	}

    /**	��ʼ���̶���ǩ��ά����cells	*/
    private void initCells() {
		for (int i = 0; i < Global.rowNum; i++) {
			for (int j = 0; j < Global.colNum; j++) {
				// ��ʼ�����и���
				cells[i][j] = new Label();
				setEntityImage(cells[i][j], null); // ���͸��ͼƬ
				cells[i][j].setScaleX(Global.span / Global.entityWidth); // ��ǩ��������
				cells[i][j].setScaleY(Global.span / Global.entityHeight);
				entityLayer.add(cells[i][j], j, i); // ���õ������ͼ��
			}
		}
    }

	/**	����entities����д���ʼ����animItems	*/
	public void initAnimItems() {
		// ���Ƴ���ǰ��ȫ��Label
		Iterator<Integer> it = animItems.keySet().iterator();
        while (it.hasNext()) {
            animLayer.getChildren().remove(animItems.get(it.next()));
            it.remove();
        }
		// ����entities����д��
		for (Entry<Integer, Entity> item : CoreWorld.entities.entrySet()) {
			Label tmpLab = new Label();
			setEntityImage(tmpLab, item.getValue().creature.getType()); // ���ͼƬ
			tmpLab.setScaleX(Global.span / Global.entityWidth); // ��ǩ��������
			tmpLab.setScaleY(Global.span / Global.entityHeight);
			tmpLab.setTranslateX(Global.marginX + item.getValue().position.col() * Global.span); // ��ǩλ��
			tmpLab.setTranslateY(Global.marginY + item.getValue().position.row() * Global.span);
			animLayer.getChildren().add(tmpLab); // ���õ�������
			animItems.put(item.getKey(), tmpLab);
		}
	}
	
	/**	��ʼ��GUIWindow.
     *	�ڼ���WorldWindow.fxml��ɺ��Զ�����	*/
    @FXML
    private void initialize() {
    	// ��ʼ������
    	Image backImg = new Image("file:img/BackGround.png");
    	background.setGraphic(new ImageView(backImg));
    	// GridPaneƫ��
    	entityLayer.setTranslateX(Global.marginX);
    	// ��ʼ��˵����ǩ
    	initLabels(false);
    	// ��ʼ��cells���޶�������animItems�����𶯻���
		initAnimItems();
		initCells();
		paintWindow(); // ���ƴ��ڣ��޶�����
		// ��ʼ��Ĭ���ж���
		entityLayer.setVisible(false);
		animLayer.setVisible(true);
		// ���ü����¼�
		receiver.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SPACE && cWrd != null) {
                	pressKeySpace();
                }
                if (event.getCode() == KeyCode.L && cWrd != null) {
                	pressKeyL();
                }
                if (event.getCode() == KeyCode.S && cWrd != null) {
                	pressKeyS();
                }
                if (event.getCode() == KeyCode.R && cWrd != null) {
                	pressKeyR();
                }
                if (event.getCode() == KeyCode.P && cWrd != null) {
                	pressKeyP();
                }
            }
        });
    }

    /**	ÿ��������R����	*/
    public void pressKeyR() {
		clearWindow(); // �����������
		System.out.println("����");
		cWrd.initAll();
		initLabels(true);
		initAnimItems();
		cWrd.showWorld();
		paintWindow(); // ������������
    }

    /**	ÿ��������P����	*/
    private void pressKeyP() {
    	if (Global.isAfterBattleNotPlayback()) // ս������ʱ���ǻط�ģʽ��
    		loadRecord(true);
    	else {
    		String head = "���ֻط�ʧ��";
    		String content = "����[ս��]ģʽ�£�����[����]����롰P���Իطű���";
    		Global.showAlertDialog(head, content);
    	}
    }

    /**	ÿ��������L����	*/
    private void pressKeyL() {
    	if (Global.isBeforeOrAfterOrPlayback()) // �ط��л�ս����ʼǰ��ս������ʱ
    		loadRecord(false);
    	else {
    		String head = "��ȡ��¼ʧ��";
    		String content = "���ڣ�(1)[�ط�]ģʽ�£��� (2)[ս��]ģʽ�µ�[׼��]ʱ��[����]�󣬼��롰L���Զ�ȡ��¼";
    		Global.showAlertDialog(head, content);
    	}
    }

    /**	��������	*/
    public void loadRecord(boolean isDefault) {
    	if (isDefault)
    		Global.recordReader = new XMLRecordReader(Global.defaultFilename); // ��ȡĬ���ļ�
    	else {
        	File xmlFile = Main.showOpenXMLChooser();
        	if (xmlFile == null) {
        		System.out.println("ȡ����ȡ��ս��¼");
        		return;
        	}
    		Global.recordReader = new XMLRecordReader(xmlFile.getPath()); // ��ȡ�ļ�
    	}
		// ��ʼ�����ڣ�׼���ط�
		clearWindow(); // �����������
		cWrd.initGlobal(true); // ��ʼ��ȫ�ֱ���
		initLabels(true);
		initAnimItems(); // ��ʼ������
		cWrd.showWorld();
		paintWindow(); // ������������
    }
    
    /**	ÿ��������S����	*/
    public void pressKeyS() {
    	if (Global.isAfterBattleNotPlayback()) { // ս������ʱ���ǻط�ģʽ��
        	File xmlFile = Main.showSaveXMLChooser();
        	if (xmlFile == null) {
        		System.out.println("ȡ�������ս��¼");
        		return;
        	}
    		Global.recordWriter.saveRecord(xmlFile.getPath()); // ����xmlս����¼
    	}
    	else {
    		String head = "�����¼ʧ��";
    		String content = "����[ս��]ģʽ�£�����[����]����롰S���Ա����¼";
    		Global.showAlertDialog(head, content);
    	}
    }
    
    /**	���ûغ��������������������̨�Լ���ʾ����ǩ	*/
    private void showRoundDescription() {
		if (Global.battlePlayingBack) {
			System.out.print("[�ط���] ");
			modeLabel.setText("�ط���...");
		}
		else {
			System.out.print("[ս����] ");
			modeLabel.setText("ս����...");
		}
		System.out.print("��" + (Global.roundNum + 1) / 2 + "�غϣ�[");
		roundLabel.setText("�غ�����" + (Global.roundNum + 1) / 2);
		if (Global.roundNum % 2 == 1) {
			System.out.print("��«��");
	    	groupLabel.setText("�ж�������«��");
		}
		else {
			System.out.print("����");
	    	groupLabel.setText("�ж���������");
		}
		System.out.println("]�ж��غ�");
    }
    
    /**	ÿ�������̿ո����	*/
	private void pressKeySpace() {
		Global.battleStart = true;
		if (!Global.battleEnd) { // ս��δ����
	    	// �غ�����1���ӡ���1�غϡ���ʼ��
			Global.roundNum += 1; 
			showRoundDescription(); // ��ʾ������Ϣ
			clearWindow();
			// �ж�
	        start(); 
	        try { // �ȴ������߳�ִ�����ٴ�ӡ
	            Thread.sleep(100);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			cWrd.showWorld();
			paintWindow();
			Global.keepFormationRound -= 1;
			// ��ʱ�洢
			if (!Global.battlePlayingBack) // �ط�ģʽ���洢
				Global.recordWriter.addRoundElements(Global.roundNum); 
			Global.battleEnd = Global.isEnded();
			// ���ս��/�ط�[����]
			if (Global.battleEnd) {
				observer.setText("  ʤ������" + Global.battleWin.label + "  ");
				observer.setVisible(true);
				if (Global.battlePlayingBack) { // �ط�ģʽ���洢
					System.out.println("�طŽ��������롰R������");
					modeLabel.setText("�طŽ���");
				}
				else { // ������Ĭ���ļ�
					System.out.println("ս�����������롰R�����ã����롰P���طű���");
					modeLabel.setText("ս������");
					Global.recordWriter.saveRecord(Global.defaultFilename);
				}
			}
		}
	}

	/**	��������ʵ����̲߳����Ŷ���	*/
	public void start() {
		for (Entity en: CoreWorld.entities.values()) {
			// ͨ���طŻ��̸߳ı�en���˶�����
			if (Global.battlePlayingBack) // �ط�ģʽ
				Global.recordReader.setEntityByRound(en, Global.roundNum);
			else if (Global.isActionRound(en.creature.getGroup()))
				en.start(); // ��ʼ�ƶ�
			else en.stand(); // ��ֹ
			
			// ���ʱ����
			Timeline timeline = new Timeline(); // ��ѭ��
	        KeyFrame keyFrame = new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {  
	            @Override  
	            public void handle(ActionEvent event) { 
	            	playAnimation(en); // ����Ч��
	            }  
	        });  
	        timeline.getKeyFrames().add(keyFrame); 
	        timeline.play(); // ��ʼ����
		}
	}
    
	/**	���ö���	*/
	private void playAnimation(Entity en) {
		if (!en.position.equals(new Point(0, 0))) {
			// �ƶ�����
			TranslateTransition tt = new TranslateTransition(Duration.millis(Global.durTime));
	        tt.setByX(en.direction.col() * Global.span); // xλ��
	        tt.setByY(en.direction.row() * Global.span); // yλ��
	        ParallelTransition animMov = new ParallelTransition(animItems.get(en.id), tt);
	        // �ŵ�ʱ������
        	animMov.play(); // �ƶ�
		}
        // ��ʧ����
        FadeTransition ft = new FadeTransition(Duration.millis(Global.durTime));
        ft.setToValue(0);
        ParallelTransition animFade = null;
        // �Լ�����һ�غ�������
		if (en.state == EntityState.DEAD) { // ���Լ�һ����ʧ����
			animFade = new ParallelTransition(animItems.get(en.id), ft);
			animFade.play();
		}
		// ����һ�غϻ����˵���
		if (en.hasWined) { // ������һ����ʧ����
			animFade = new ParallelTransition(animItems.get(en.enemy.id), ft);
			animFade.play();
		}
	}
	
	/**	�����������ͻ�ȡ��Ӧ��ͼ��	*/
	private Image getEntityImage(CreatureType ctype) {
    	Image creatureImg = null;
    	if (ctype == null)
    		creatureImg = new Image("file:img/none.png");
			//creatureImg = new Image("file:img/debug.png");
    	else
    		creatureImg = new Image("file:img/" + ctype.imgName + ".png");
    	return creatureImg;
	}
	
	/**	����һ�����ӵ�ͼ��	*/
    private void setEntityImage(Label cellLabel, CreatureType ctype) {
    	cellLabel.setGraphic(new ImageView(getEntityImage(ctype)));
    }

    /**	���ȫ�������޶�����	*/
    public void clearWindow() {
		for (Entity en: CoreWorld.entities.values()) {
			if (en.state == EntityState.LIVE) {
				setEntityImage(cells[en.position.row()][en.position.col()], null);
			}
		}
    }
    
    /**	����entities����ȫ�������޶�����	*/
    public void paintWindow() {
		for (Entity en: CoreWorld.entities.values()) {
			if (en.state == EntityState.LIVE) 
				setEntityImage(cells[en.position.row()][en.position.col()], en.creature.getType());
		}
	}
}
