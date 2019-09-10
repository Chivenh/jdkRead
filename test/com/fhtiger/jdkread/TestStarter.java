package com.fhtiger.jdkread;

import com.sun.istack.internal.NotNull;

/**
 * TestStarter
 *
 * @author LFH
 * @since 2019年09月02日 20:37
 */
public abstract class TestStarter {

	protected static void out(@NotNull Object arg){
		System.out.print(" "+arg.toString());
	}

	protected static void  out(Object ... args){
		for (Object arg : args) {
			out(arg);
		}
	}

	protected static void br(){
		System.out.println();
	}

	protected static void outr(@NotNull Object arg){
		out(arg);
		br();
	}

	protected static void  outr(Object ... args){
		out(args);
		br();
	}

	protected static void outf(String format,Object... args){
		System.out.printf(format,args);
	}

	protected static void outfr(String format,Object... args){
		outf(format,args);
		br();
	}

}
