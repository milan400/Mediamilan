/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamilan;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

/**
 *
 * @author kiran
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private String filepath;
    
    @FXML
    private MediaPlayer mediaplayer;
    
    @FXML
    private MediaView nediaView;
    
    @FXML
    private Slider slider;
    
    @FXML
    private Slider seekslider;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        FileChooser filechooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("select a File (*.mp4)", "*.mp4");
        filechooser.getExtensionFilters().add(filter);
        File file = filechooser.showOpenDialog(null);
        
        filepath = file.toURI().toString();
        
        if(filepath!=null){
        Media media = new Media(filepath);
        mediaplayer = new MediaPlayer(media);
        nediaView.setMediaPlayer(mediaplayer);
        
                DoubleProperty width = nediaView.fitWidthProperty();
                DoubleProperty height = nediaView.fitHeightProperty();
                
                width.bind(Bindings.selectDouble(nediaView.sceneProperty(),"width"));
                height.bind(Bindings.selectDouble(nediaView.sceneProperty(),"height"));
                
                slider.setValue(mediaplayer.getVolume()*100);
                slider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mediaplayer.setVolume(slider.getValue()/100);
            }
        });
                mediaplayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
             seekslider.setValue(newValue.toSeconds());
            }
        });
                seekslider.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               
                mediaplayer.seek(Duration.seconds(seekslider.getValue()));
            }
        });
              
                
        mediaplayer.play();
        }
    }
    
    @FXML
    private void pauseVideo(ActionEvent event)
    {
        mediaplayer.pause();
        mediaplayer.setRate(1);
    }
    @FXML
    private void playVideo(ActionEvent event)
    {
        mediaplayer.play();
    }
    @FXML
    private void stopVideo(ActionEvent event)
    {
        mediaplayer.stop();
    }
    @FXML
    private void fastVideo(ActionEvent event)
    {
        mediaplayer.setRate(1.5);
    }
    @FXML
    private void fasterVideo(ActionEvent event)
    {
        mediaplayer.setRate(2);
    }
    @FXML
    private void slowVideo(ActionEvent event)
    {
        mediaplayer.setRate(0.75);
    }
    @FXML
    private void slowerVideo(ActionEvent event)
    {
        mediaplayer.setRate(0.5);
    }
    @FXML
    private void exitVideo(ActionEvent event)
    {
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
      
    
}














































