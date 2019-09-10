package com.fhtiger.jdkread.java.util;

import com.fhtiger.jdkread.TestStarter;
import com.sun.jmx.remote.internal.ArrayQueue;
import org.junit.Test;

import java.util.Stack;

/**
 * StackTest
 *
 * @author LFH
 * @since 2019年09月10日 14:37
 */
public class StackTest extends TestStarter {

	@Test
	public void withStreamApiTest(){

		Stack<Integer> ms = new Stack<>();

		Integer index=1;

		ms.push(index++);//1
		ms.push(index++);//2
		ms.push(index++);//3
		ms.push(index);//4

		ms.forEach(StackTest::out);

		ArrayQueue<Integer> ads = new ArrayQueue<>(4);

		ads.addAll(ms);

		ads.forEach(StackTest::out);
	}
}
