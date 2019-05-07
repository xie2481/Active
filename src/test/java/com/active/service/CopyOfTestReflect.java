package com.active.service;

import org.junit.Test;

import com.active.entity.Chapter;

public class CopyOfTestReflect extends ReflectFilters {

	@Test
	public void TestM() {
		Chapter chapter = new Chapter();
		chapter.setChaName("章节名");
		//System.out.println(DoMethod("Method_1", chapter));
		//System.out.println(DoMethod("Method_2", null));
	}

	public String Method_1(Chapter chapter) {
		return chapter.getChaName();
	}

	public void Method_2() {
		//System.out.println("123");
	}

}
