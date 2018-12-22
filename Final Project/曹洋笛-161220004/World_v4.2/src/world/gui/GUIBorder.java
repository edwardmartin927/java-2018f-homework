package world.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;

import world.*;
import world.util.*;


public class GUIBorder {

	/**	CoreWorldָ��	*/
	private CoreWorld cWrd = null;
	/**	GUIWindowָ��	*/
	private GUIWindow gWin = null;
	
	@FXML
    private MenuItem menuItReset; // ����
	@FXML
    private MenuItem menuItSaveAs; // ����Ϊ
	@FXML
    private MenuItem menuItReadFrom; // ��ȡ
	@FXML
	private MenuItem menuBroItHY; // ��«�޺�����
	@FXML
	private MenuItem menuBroItYX; // ��«��������
	@FXML
	private MenuItem menuBroItCE; // ��«�޳�����
	@FXML
	private MenuItem menuBroItCS; // ��«�޳�����
	@FXML
	private MenuItem menuBroItYL; // ��«��������
	@FXML
	private MenuItem menuBroItFY; // ��«�޷�����
	@FXML
	private MenuItem menuBroItYY; // ��«��������
	@FXML
	private MenuItem menuBroItFS; // ��«�޷�ʸ��
	@FXML
	private MenuItem menuMonItHY; // ���ֺ�����
	@FXML
	private MenuItem menuMonItYX; // ����������
	@FXML
	private MenuItem menuMonItCE; // ���ֳ�����
	@FXML
	private MenuItem menuMonItCS; // ���ֳ�����
	@FXML
	private MenuItem menuMonItYL; // ����������
	@FXML
	private MenuItem menuMonItFY; // ���ַ�����
	@FXML
	private MenuItem menuMonItYY; // ����������
	@FXML
	private MenuItem menuMonItFS; // ���ַ�ʸ��

	@FXML
	private CheckMenuItem menuCheckAnim; // �Ƿ���ʾ����
	
	/**	��ʼ��GUIBorder.
     *	�ڼ���WorldBorder.fxml��ɺ��Զ�����	*/
    @FXML
    private void initialize() {
    	menuItReset.setOnAction((ActionEvent t) -> {
    		handleReset();
        });
    	menuItSaveAs.setOnAction((ActionEvent t) -> {
    		handleSaveAs();
        });
    	menuItReadFrom.setOnAction((ActionEvent t) -> {
    		handleReadFrom();
        });
    	menuBroItHY.setOnAction((ActionEvent t) -> {
    		handleForm(FormationType.HY, GroupType.Bro);
        });
    	menuBroItYX.setOnAction((ActionEvent t) -> {
    		handleForm(FormationType.YX, GroupType.Bro);
        });
    	menuBroItCE.setOnAction((ActionEvent t) -> {
    		handleForm(FormationType.CE, GroupType.Bro);
        });
    	menuBroItCS.setOnAction((ActionEvent t) -> {
    		handleForm(FormationType.CS, GroupType.Bro);
        });
    	menuBroItYL.setOnAction((ActionEvent t) -> {
    		handleForm(FormationType.YL, GroupType.Bro);
        });
    	menuBroItFY.setOnAction((ActionEvent t) -> {
    		handleForm(FormationType.FY, GroupType.Bro);
        });
    	menuBroItYY.setOnAction((ActionEvent t) -> {
    		handleForm(FormationType.YY, GroupType.Bro);
        });
    	menuBroItFS.setOnAction((ActionEvent t) -> {
    		handleForm(FormationType.FS, GroupType.Bro);
        });
    	menuMonItHY.setOnAction((ActionEvent t) -> {
    		handleForm(FormationType.HY, GroupType.Mon);
        });
    	menuMonItYX.setOnAction((ActionEvent t) -> {
    		handleForm(FormationType.YX, GroupType.Mon);
        });
    	menuMonItCE.setOnAction((ActionEvent t) -> {
    		handleForm(FormationType.CE, GroupType.Mon);
        });
    	menuMonItCS.setOnAction((ActionEvent t) -> {
    		handleForm(FormationType.CS, GroupType.Mon);
        });
    	menuMonItYL.setOnAction((ActionEvent t) -> {
    		handleForm(FormationType.YL, GroupType.Mon);
        });
    	menuMonItFY.setOnAction((ActionEvent t) -> {
    		handleForm(FormationType.FY, GroupType.Mon);
        });
    	menuMonItYY.setOnAction((ActionEvent t) -> {
    		handleForm(FormationType.YY, GroupType.Mon);
        });
    	menuMonItFS.setOnAction((ActionEvent t) -> {
    		handleForm(FormationType.FS, GroupType.Mon);
        });
    	menuCheckAnim.setSelected(true);
    	menuCheckAnim.setOnAction((ActionEvent t) -> {
    		handleSetAnim();
        });
    }
    
    /**	��ʼ��GUIWindow��CoreWorld	*/
    public void setWin(GUIWindow gw) {
    	gWin = gw;
    	cWrd = gw.cWrd;
    }
	
    /**	�˵��� ���� ����	*/
	private void handleReset() {
		if (cWrd != null && gWin != null) 
			gWin.pressKeyR();
		else System.out.println("error@GUIBorder: δ����CharWindow/GUIWindow");
	}

    /**	�˵��� ���� ����Ϊ	*/
	private void handleSaveAs() {
		gWin.pressKeyS();
	}

    /**	�˵��� ���� ��ȡ	*/
	private void handleReadFrom() {
		gWin.loadRecord(false);
	}

    /**	�˵��� ���� �ı�����	*/
	private void handleForm(FormationType ftype, GroupType gtype) {
		if (!Global.battlePlayingBack) { // ս��ģʽ
			if (cWrd != null && gWin != null) {
				gWin.clearWindow(); // �����������
				System.out.println("�ı�" + gtype.label + "������" + ftype.name);
				cWrd.initFormation(ftype, gtype);
				gWin.initLabels(true);
				gWin.initAnimItems();
				cWrd.showWorld();
				gWin.paintWindow(); // ������������
			}
			else System.out.println("error@GUIBorder: δ����CharWindow/GUIWindow");
		}
    	else {
    		String head = "�ı�����ʧ��";
    		String content = "����[ս��]ģʽ�¸ı�����";
    		Global.showAlertDialog(head, content);
    	}
	}

    /**	�˵����ѡ�� ���� ��ʾ����	*/
	private void handleSetAnim() {
		if (menuCheckAnim.isSelected()) {
			gWin.animLayer.setVisible(true);
			gWin.entityLayer.setVisible(false);
		}
		else {
			gWin.animLayer.setVisible(false);
			gWin.entityLayer.setVisible(true);
		}
	}
}
