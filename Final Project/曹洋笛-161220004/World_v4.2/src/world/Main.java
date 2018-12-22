package world;
	
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import world.gui.GUIBorder;
import world.gui.GUIWindow;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;


public class Main extends Application {
	
	/**	����̨������������	*/
	public static Stage worldStage;
	/**	��ʾ�˵���	*/
    private BorderPane worldBorder;
    /**	��ʾ���ݣ�����+����Լ���������	*/
    private StackPane worldWindow;
    /**	��������	*/
    private CoreWorld cWrd;
    /**	��Ӧ��WorldWindow.fxml	*/
    private GUIWindow gWin;
    /**	��Ӧ��WorldBorder.fxml	*/
    private GUIBorder gFrame;
	
	@Override
	public void start(Stage primaryStage) {
		// ��ʼ��CoreWorld
		cWrd = new CoreWorld();
		cWrd.showWorld();
		try { // ��ʼ��GUI
			primaryStage.setTitle("��«�޴�ս����");
			primaryStage.getIcons().add(new Image("file:img/icon.png"));
			primaryStage.setWidth(Global.winWidth);
			primaryStage.setHeight(Global.winHeight);
			primaryStage.setResizable(false); // ��ֹ��������
			worldStage = primaryStage;
			initGUIWorld();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**	��ʼ��GUI	*/
	private void initGUIWorld() {
        try {
	        // ��WorldWindow.fxml����StackPane
            FXMLLoader windowLoader = new FXMLLoader();
            windowLoader.setLocation(Main.class.getResource("gui/WorldWindow.fxml"));
	        worldWindow = (StackPane)windowLoader.load();
	        // ��ʼ��GUIWindow
	        gWin = (GUIWindow)windowLoader.getController();
	        if (gWin != null)
	        	gWin.setWin(cWrd);
	        else System.out.println("error@Main: ����GUIWindowʧ��");

            // ��WorldBorder.fxml����BorderPane
            FXMLLoader borderLoader = new FXMLLoader();
            borderLoader.setLocation(Main.class.getResource("gui/WorldBorder.fxml"));
            worldBorder = (BorderPane)borderLoader.load();
	        // ��ʼ��GUIBorder
	        gFrame = (GUIBorder)borderLoader.getController();
	        if (gFrame != null)
	        	gFrame.setWin(gWin);
	        else System.out.println("error@Main: ����GUIBorderʧ��");
	        
	        // ��ʾworldWindow�ڵĳ���
	        worldBorder.setCenter(worldWindow);
            // ��ʾworldBorder�ڵĳ���
            Scene scene = new Scene(worldBorder);
            worldStage.setScene(scene);
            worldStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	/**	��ʾ���ļ����ļ�ѡ����	*/
	public static File showOpenXMLChooser() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("��ս����¼");
		fileChooser.setInitialDirectory(new File(Global.defaultPath)); // Ĭ��·��
        FileChooser.ExtensionFilter xmlFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(xmlFilter); // �ļ���������
        File xmlFile = fileChooser.showOpenDialog(worldStage);
        //System.out.println(xmlFile.getPath());
        return xmlFile;
	}

	/**	��ʾ�����ļ����ļ�ѡ����	*/
	public static File showSaveXMLChooser() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("����ս����¼��");
		fileChooser.setInitialDirectory(new File(Global.defaultPath)); // Ĭ��·��
        FileChooser.ExtensionFilter xmlFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(xmlFilter); // �ļ���������
        File xmlFile = fileChooser.showSaveDialog(worldStage);
        //System.out.println(xmlFile.getPath());
        return xmlFile;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
