package com.test;

import javazoom.jl.player.Player;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @className
 * @description
 * @author fengjun
 * @date 2022年03月09日 下午9:50
 */
public class Test {

    public static final String path = "/Users/fengjun/Music/那女孩对我说.mp3";

    public static void main(String[] args) {


            playMusic2();
    }

    /**
     * Java Music 获取歌曲播放时长
     * @Title: get_music_play_length
     * @Description: 获取歌曲播放时长
     * @throws InvalidAudioFrameException
     * @throws ReadOnlyFileException
     * @throws TagException
     * @throws IOException
     * @throws CannotReadException
     * @date 2019年10月25日 下午12:28:41
     */
    public static void palyMusic(){
        try {
            File file=new File(path);
            AudioFile mp3= AudioFileIO.read(file);
            mp3.getAudioHeader().getTrackLength();
        } catch (Exception e) {
        }
    }


    /**
     * 声明一个全局的player对象
     */
    public static Player player = null;

    public static void playMusic2(){
        try {
            //声明一个File对象
            File mp3 = new File(path);

            //创建一个输入流
            FileInputStream fileInputStream = new FileInputStream(mp3);

            //创建一个缓冲流
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

            //创建播放器对象，把文件的缓冲流传入进去
            player = new Player(bufferedInputStream);


            //调用播放方法进行播放
            player.play();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
