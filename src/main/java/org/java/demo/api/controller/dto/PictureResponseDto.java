package org.java.demo.api.controller.dto;

import java.util.List;

import org.java.demo.pojo.Picture;
import org.springframework.validation.BindingResult;

public class PictureResponseDto {

	private List<Picture> pictures;
	private BindingResult bindingResult;
	
	public PictureResponseDto(List<Picture> pictures) {
		setPictures(pictures);
	}
	public PictureResponseDto(BindingResult bindingResult) {
		setBindingResult(bindingResult);
	}
	public PictureResponseDto(List<Picture> pictures, BindingResult bindingResult) {
		setPictures(pictures);
		setBindingResult(bindingResult);
	}
	
	public List<Picture> getPictures() {
		return pictures;
	}
	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
	public BindingResult getBindingResult() {
		return bindingResult;
	}
	public void setBindingResult(BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}
}
