package world;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import world.util.*;
import world.xml.*;

/**	
 *	ȫ�ֳ���������ȫ�ֺ���.
 *	��������ֵ������λ�ã�ʤ�����ʣ���������
 *	
 *	@author Mirror
 */
public final class Global {

	/**	���� {@value}	*/
	public static final int rowNum = 17; // 0 ~ 16
	
	/**	���� {@value}	*/
	public static final int colNum = 18; // 0 ~ 17

	/**	������ {@value}	*/
	public static final double span = 50;

	/**	����ͼƬ�� {@value}	*/
	public static final double groundWidth = colNum * span; // 900
	
	/**	����ͼƬ��  {@value}	*/
	public static final double groundHeight = rowNum * span; // 850

	/**	����λ�� {@value}	*/
	public static final double winLocationXY = 0;
	
	/**	���ڿ� {@value}	*/
	public static final double winWidth = 906;
	
	/**	���ڸ� {@value}	*/
	public static final double winHeight = 917;

	/**	ʵ��ͼƬ�� {@value}	*/
	public static final double entityWidth = 100;
	
	/**	ʵ��ͼƬ�� {@value}	*/
	public static final double entityHeight = 100;
	
	/**	˵����ǩ��ÿ���ֵĿ� {@value}	*/
	public static final double labelFontWidth = 24;

	/**	˵����ǩ�� {@value}	*/
	public static final double labelWidth(int n) { return (labelFontWidth * n); }
	
	/**	˵����ǩ�� {@value}	*/
	public static final double labelHeight = 48;
	
	/**	GridPane��ƫ��x {@value}	*/
	public static final double marginX = -span / 2; // -25
	
	/**	GridPane��ƫ��y {@value}	*/
	public static final double marginY = (span - groundHeight) / 2; // -400

	/**	Label��ƫ��x {@value}	*/
	public static final double marginLabelX(int n) { return (labelWidth(n) - groundWidth) / 2; }
	
	/**	Label��ƫ��y {@value}	*/
	public static final double marginLabelY = (labelHeight - groundHeight) / 2;
	
	/**	���ս�����ĵ�	*/
	public static final Point leftCenterP = new Point(7, 4);

	/**	�Ҳ�ս�����ĵ�	*/
	public static final Point rightCenterP = new Point(7, 13);	

	/**	˫������ԭ���͵İ�غ���	*/
	public static final int keepFormationRoundNum = 2;
	
	/**	˫������ԭ���͵İ�غ���	*/
	public static int keepFormationRound = keepFormationRoundNum;

	/**	����ʱ��«�޵�ʤ��	*/
	public static final int BroWinPercent = 50;
	
	/**	����ʱ���ֵ�ʤ��	*/
	public static final int MonWinPercent = 100 - BroWinPercent;
	
	/**	��������ʱ��(ms)	*/
	public static final int durTime = 500;

	/**	ս���غ���	*/
	public static int roundNum = 0;

	/**	�ж��Ƿ��Ǳ���Ӫ���ж��غ�	*/
	public static boolean isActionRound(GroupType type) {
		if (roundNum % 2 == 1)
			return (type == GroupType.Bro);
		else return (type == GroupType.Mon);
	}
	
	/**	ս���Ƿ�ʼ	*/
	public static boolean battleStart = false;
	
	/**	ս���Ƿ����	*/
	public static boolean battleEnd = false;

	/**	ս��ʤ����	*/
	public static GroupType battleWin;
	
	/**	�ж�ս���Ƿ����	*/
	public static boolean isEnded() {
		boolean hasBro = false;
		boolean hasMon = false;
		for (Entity en: CoreWorld.entities.values()) {
			if ((en.state == EntityState.LIVE) && (en.creature.getGroup() == GroupType.Bro))
				hasBro = true;
			if ((en.state == EntityState.LIVE) && (en.creature.getGroup() == GroupType.Mon))
				hasMon = true;
		}
		if (hasBro && hasMon)
			return false;
		else {
			if (hasBro) battleWin = GroupType.Bro;
			else battleWin = GroupType.Mon;
			return true;
		}
	}
	
	/**	�Ƿ����ڻط�ս������	*/
	public static boolean battlePlayingBack = false;
	
	/**	����ս��	*/
	public static XMLRecordWriter recordWriter = null;
	
	/**	��ȡս��	*/
	public static XMLRecordReader recordReader = null;
	
	/**	Ĭ�ϱ����ļ�	*/
	public static final String defaultFilename = "records/defaultRecordFile.xml";

	/**	Ĭ�ϱ���·��	*/
	public static final String defaultPath = "records/";
	
	/**	ս�������󣨷ǻط�ģʽ��	*/
	public static boolean isAfterBattleNotPlayback() {
		return (!battlePlayingBack && battleStart && battleEnd);
	}
	
	/**	ս����ʼǰ��ս���������ط�ʱ	*/
	public static boolean isBeforeOrAfterOrPlayback() {
		if (battlePlayingBack)
			return true;
		if ((!battleStart && !battleEnd) || (battleStart && battleEnd))
			return true;
		return false;
	}

    /**	������ʾ��
     *	@param head ��ʾ���������
     *	@param content ��ϸ˵��	*/
	public static void showAlertDialog(String head, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("��ʾ");
		alert.initOwner(Main.worldStage);
		alert.setHeaderText(head);
		alert.setContentText(content);
		alert.showAndWait();
	}
    
}
