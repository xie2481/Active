package com.active.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.active.entity.Document;
import com.active.entity.Exam;
import com.active.entity.Training;
import com.active.entity.Video;


public interface SearchDao {

	/**
	 * ËÑË÷Â¼Ïñ
	 */
	@Select("SELECT * FROM video WHERE VType=1 AND VName LIKE concat('%',concat(#{keyword},'%'))")
	List<Video> searchVideo(String keyword);

	/**
	 * ËÑË÷ÎÄ¼þ
	 */
	@Select("SELECT * FROM document WHERE DocName LIKE concat('%',concat(#{keyword},'%'))")
	List<Document> searchDoc(String keyword);

	/**
	 * ËÑË÷¶¯»­
	 */
	@Select("SELECT * FROM video WHERE VType=2 AND VName LIKE concat('%',concat(#{keyword},'%'))")
	List<Video> searchFlash(String keyword);

	/**
	 * ËÑË÷Á·Ï°
	 */
	@Select("SELECT * FROM training WHERE TrainName LIKE concat('%',concat(#{keyword},'%'))")
	List<Training> searchTraining(String keyword);

	/**
	 * ËÑË÷ÊÔ¾í
	 */
	@Select("SELECT * FROM exam WHERE EName LIKE concat('%',concat(#{keyword},'%'))")
	List<Exam> searchExam(String keyword);

}
