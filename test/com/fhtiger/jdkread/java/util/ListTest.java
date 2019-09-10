package com.fhtiger.jdkread.java.util;

import com.fhtiger.jdkread.TestStarter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ListTest
 *
 * @author LFH
 * @since 2019年09月10日 14:47
 */
public class ListTest extends TestStarter {

	@Test
	public void arrayListTest(){

		List<Integer> list1 = new ArrayList<>();
		List<Integer> list2 = new ArrayList<>();

		Integer index = 1;

		list1.add(5);
		list1.addAll(Arrays.asList(index++,index++,index++,index));

		list2.add(index*index++);
		list2.add(index*index++);
		list2.add(index*index);

	}
}
