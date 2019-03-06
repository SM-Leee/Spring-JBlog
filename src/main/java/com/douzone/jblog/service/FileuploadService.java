package com.douzone.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileuploadService {
	
	private static final String SAVE_PATH = "/uploads";
	private static final String URL = "/uploads/images";
	
	public String restore(MultipartFile multipartFile) {
		String url = "";
		
		try {
		
		// 파일이 비어있는지 확인한다.
		if(multipartFile.isEmpty()) {
			return url;
		}
		
		//file의 이름을 확인
		String originalFileName = multipartFile.getOriginalFilename();
		//확장자 분리
		String extName = originalFileName.substring(originalFileName.lastIndexOf('.')+1);
		//저장할 파일 이름 (generateSaveFileName 을 함수로 만들어 주면 좋다.)
		String saveFileName = generateSaveFileName(extName);
		//file의 size 확인
		long fileSize = multipartFile.getSize();
		
		System.out.println("##############"+originalFileName);
		System.out.println("##############"+extName);
		System.out.println("##############"+saveFileName);
		System.out.println("##############"+fileSize);
		
		byte[] fileData = multipartFile.getBytes();
		OutputStream os = new FileOutputStream(SAVE_PATH+"/"+saveFileName);
		os.write(fileData);
		os.close();
		
		url = URL+"/"+saveFileName;
		
		} catch (IOException e) {
			new RuntimeException("upload fail");
		}
		return url;
	}
	
	private String generateSaveFileName(String extName) {
		String fileName = "";
		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += ("." + extName);
		
		return fileName;
	}
}
