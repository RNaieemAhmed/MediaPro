package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

public class SampleController implements Initializable{
	
	
	@FXML
	private Button play;
	@FXML
	private Button refresh;
	@FXML
	private Button pause;
	@FXML
	private Button forward;
	@FXML
	private Button rewind;
	@FXML
	private Button stop;
	@FXML
	private MediaView media;
	private MediaPlayer mp;
	private Media m;
	
	@FXML
	private File file;
	@FXML
	private Slider volumeSlider;
	
	
	
	public void playVideo(MouseEvent m) {
		
		if(file!=null) {
//			play.setVisible(false);
//			pause.setVisible(true);	
			play.setDisable(true);
			pause.setDisable(false);
			mp.play();
			
		}
		
	}
	
	public void pauseVideo(MouseEvent m) {
		
		if(file!=null) {
//			play.setVisible(true);
//			pause.setVisible(false);
			pause.setDisable(true);
			play.setDisable(false);
			mp.pause();			
		}
		
	}
	
	public void lastVideo(MouseEvent m) {
		if(file!=null) {		
//		play.setVisible(true);
//		pause.setVisible(false);
		pause.setDisable(true);
		play.setDisable(false);
		mp.seek(mp.getTotalDuration());
		mp.stop();
		
		}
	}
	public void fastVideo(MouseEvent m) {
		if(file!=null) {
		mp.setRate(2);
		
		}
	}
	public void slowVideo(MouseEvent m) {
		if(file!=null) {
			mp.setRate(-2);
		}
	}
	public void reloadVideo(MouseEvent m) {
		if(file!=null) {
		mp.seek(mp.getStartTime());
		mp.play();
		
		}
	}
	
	public void openVideo(ActionEvent e) {
		
		FileChooser filechooser =new FileChooser();
		filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Video ", "*.mp4","*.mkv"));
		file=filechooser.showOpenDialog(null);
		String path=file.getAbsolutePath();		
		path=path.replace("\\", "/");
		if(file!=null) {
			m=new Media(new File(path).toURI().toString());
			//C:\Users\NaieemAhmed\Videos\VID_20211122_131659.mp4---without URI
			//file:/C:/Users/NaieemAhmed/Videos/VID_20211122_131659.mp4---with URI
			mp=new MediaPlayer(m);
			media.setMediaPlayer(mp);
			mp.play();
//			play.setVisible(false);
//			pause.setVisible(true);
			play.setDisable(true);
			pause.setDisable(false);
			
			volumeSlider.setValue(mp.getVolume()*100);
			volumeSlider.valueProperty().addListener(new InvalidationListener() {

				@Override
				public void invalidated(Observable arg0) {
					mp.setVolume(volumeSlider.getValue()/100);
				}
				
			});
			DoubleProperty width=media.fitWidthProperty();
			DoubleProperty height=media.fitHeightProperty();
			width.bind(Bindings.selectDouble(media.sceneProperty(), "width"));
			height.bind(Bindings.selectDouble(media.sceneProperty(), "height"));
		}
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	
}
