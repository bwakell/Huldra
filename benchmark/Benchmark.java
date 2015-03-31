import java.util.*;
import java.io.*;
import java.math.BigInteger;
import org.apfloat.*;
import org.jscience.mathematics.number.*;

public class Benchmark
{
	/**
	* Where we store time stamps to measure time.
	*/
	private static long time;
	/**
	* Fills the [len] first positions of [num] with random digits using [seed] as the seed for the Random class.
	* The leading digit won't be 0.
	*/
	private static void getNum(final char[] num, final int len, final int seed)
	{
		final Random rnd = new Random(seed);
		num[0] = (char)('1' + rnd.nextInt(9));
		for(int i = 1; i<len; i++) num[i] = (char)('0'+rnd.nextInt(10));
	}
	/**
	* Fills the [len] first positions of [num] with random ints using [seed] as the seed for the Random class.
	* Should the most significant limb num[len-1] be set to zero by the random number generator it'll be altered to one.
	*/
	private static void getBigNum(final int[] num, final int len, final int seed)
	{
		final Random rnd = new Random(seed);
		for(int i = 0; i<len; i++) num[i] = rnd.nextInt();
		if(num[len-1]==0) num[len-1] = 1;
	}
	/**
	* Aims to evaluate the speed of general addition.
	*/
	public static void runAdd(final int nr)
	{
		System.out.printf("Run #%d of Addition\n", nr);
		final int len = 100_000, loop = 100_000;
		final char[] a = new char[len], b = new char[len];
		getNum(a,len,12345); getNum(b,len,67890);
		System.out.println("Numbers generated");

		System.out.println("-");
		time = System.currentTimeMillis();
		BigInteger aa = new BigInteger(new String(a)), bb = new BigInteger(new String(b));
		time = System.currentTimeMillis() - time;
		System.out.println("BigInteger parsing " + time/1000.0 + "s");
		time = System.currentTimeMillis();
		BigInteger facit = aa;
		for(int i = 0; i<loop; i++) facit = facit.add(bb);
		time = System.currentTimeMillis() - time;
		System.out.println("BigInteger add " + time/1000.0 + "s");

		System.out.println("-");
		time = System.currentTimeMillis();
		BigInt ma = new BigInt(a), mb = new BigInt(b);
		time = System.currentTimeMillis() - time;
		System.out.println("BigInt parsing " + time/1000.0 + "s");
		time = System.currentTimeMillis();
		for(int i = 0; i<loop; i++) ma.add(mb);
		time = System.currentTimeMillis() - time;
		System.out.println("BigInt add " + time/1000.0 + "s");

		System.out.println("-");
		time = System.currentTimeMillis();
		Apint apa = new Apint(new String(a)), apb = new Apint(new String(b));
		time = System.currentTimeMillis() - time;
		System.out.println("Apint parsing " + time/1000.0 + "s");
		time = System.currentTimeMillis();
		Apint apr = apa;
		for(int i = 0; i<loop; i++) apr = apr.add(apb);
		time = System.currentTimeMillis() - time;
		System.out.println("Apint add " + time/1000.0 + "s");

		System.out.println("-");
		time = System.currentTimeMillis();
		LargeInteger lia = LargeInteger.valueOf(new String(a)), lib = LargeInteger.valueOf(new String(b));
		time = System.currentTimeMillis() - time;
		System.out.println("LargeInteger parsing " + time/1000.0 + "s");
		time = System.currentTimeMillis();
		LargeInteger lir = lia;
		for(int i = 0; i<loop; i++) lir = lir.plus(lib);
		time = System.currentTimeMillis() - time;
		System.out.println("LargeInteger add " + time/1000.0 + "s");

		System.out.println("-");
		time = System.currentTimeMillis();
		String ans = facit.toString();
		time = System.currentTimeMillis() - time;
		System.out.println("BigInteger toString() " + time/1000.0 + "s");

		time = System.currentTimeMillis();
		String ans2 = ma.toString();
		time = System.currentTimeMillis() - time;
		System.out.println("BigInt toString() " + time/1000.0 + "s");

		time = System.currentTimeMillis();
		String ans3 = apr.toString(true);
		time = System.currentTimeMillis() - time;
		System.out.println("Apint toString() " + time/1000.0 + "s");

		time = System.currentTimeMillis();
		String ans4 = lir.toString();
		time = System.currentTimeMillis() - time;
		System.out.println("LargeInteger toString() " + time/1000.0 + "s");

		System.out.println("-");
		System.out.println("BigInt Check: " + ans.equals(ans2) +" "+ans.length()+" "+ans2.length());
		System.out.println("Apint Check: " + ans.equals(ans3));
		System.out.println("LargeInteger Check: " + ans.equals(ans4));

		System.out.println("--------------------------------------\n");
	}
	/**
	* Aims to evaluate the speed of general subtraction.
	*/
	public static void runSub(final int nr)
	{
		System.out.printf("Run #%d of Subtraction\n", nr);
		final int len = 100_000, loop = 100_000;
		final char[] a = new char[len], b = new char[len/4*3];
		getNum(a,len,834568); getNum(b,len/4*3,1903042);
		System.out.println("Numbers generated");

		System.out.println("-");
		time = System.currentTimeMillis();
		BigInteger aa = new BigInteger(new String(a)), bb = new BigInteger(new String(b));
		time = System.currentTimeMillis() - time;
		System.out.println("BigInteger parsing " + time/1000.0 + "s");
		time = System.currentTimeMillis();
		BigInteger facit = aa;
		for(int i = 0; i<loop; i++) facit = facit.subtract(bb);
		time = System.currentTimeMillis() - time;
		System.out.println("BigInteger sub " + time/1000.0 + "s");

		System.out.println("-");
		time = System.currentTimeMillis();
		BigInt ma = new BigInt(a), mb = new BigInt(b);
		time = System.currentTimeMillis() - time;
		System.out.println("BigInt parsing " + time/1000.0 + "s");
		time = System.currentTimeMillis();
		for(int i = 0; i<loop; i++) ma.sub(mb);
		time = System.currentTimeMillis() - time;
		System.out.println("BigInt sub " + time/1000.0 + "s");

		System.out.println("-");
		time = System.currentTimeMillis();
		Apint apa = new Apint(new String(a)), apb = new Apint(new String(b));
		time = System.currentTimeMillis() - time;
		System.out.println("Apint parsing " + time/1000.0 + "s");
		time = System.currentTimeMillis();
		Apint apr = apa;
		for(int i = 0; i<loop; i++) apr = apr.subtract(apb);
		time = System.currentTimeMillis() - time;
		System.out.println("Apint sub " + time/1000.0 + "s");

		System.out.println("-");
		time = System.currentTimeMillis();
		LargeInteger lia = LargeInteger.valueOf(new String(a)), lib = LargeInteger.valueOf(new String(b));
		time = System.currentTimeMillis() - time;
		System.out.println("LargeInteger parsing " + time/1000.0 + "s");
		time = System.currentTimeMillis();
		LargeInteger lir = lia;
		for(int i = 0; i<loop; i++) lir = lir.minus(lib);
		time = System.currentTimeMillis() - time;
		System.out.println("LargeInteger sub " + time/1000.0 + "s");

		System.out.println("-");
		time = System.currentTimeMillis();
		String ans = facit.toString();
		time = System.currentTimeMillis() - time;
		System.out.println("BigInteger toString() " + time/1000.0 + "s");

		time = System.currentTimeMillis();
		String ans2 = ma.toString();
		time = System.currentTimeMillis() - time;
		System.out.println("BigInt toString() " + time/1000.0 + "s");

		time = System.currentTimeMillis();
		String ans3 = apr.toString(true);
		time = System.currentTimeMillis() - time;
		System.out.println("Apint toString() " + time/1000.0 + "s");

		time = System.currentTimeMillis();
		String ans4 = lir.toString();
		time = System.currentTimeMillis() - time;
		System.out.println("LargeInteger toString() " + time/1000.0 + "s");

		System.out.println("-");
		System.out.println("BigInt Check: " + ans.equals(ans2) +" "+ans.length()+" "+ans2.length());
		System.out.println("Apint Check: " + ans.equals(ans3));
		System.out.println("LargeInteger Check: " + ans.equals(ans4));

		System.out.println("--------------------------------------\n");
	}
	/**
	* Aims to evaluate the speed of general multiplication.
	*/
	public static void runMul(final int nr)
	{
		System.out.printf("Run #%d of Multiplication\n", nr);
		final int len = 300, loop = 1000;
		final char[] a = new char[len], b = new char[len];
		getNum(a,len,1337); getNum(b,len,1990);
		System.out.println("Numbers generated");

		System.out.println("-");
		time = System.currentTimeMillis();
		BigInteger aa = new BigInteger(new String(a)), bb = new BigInteger(new String(b));
		time = System.currentTimeMillis() - time;
		System.out.println("BigInteger parsing " + time/1000.0 + "s");
		time = System.currentTimeMillis();
		BigInteger facit = aa;
		for(int i = 0; i<loop; i++) facit = facit.multiply(bb);
		time = System.currentTimeMillis() - time;
		System.out.println("BigInteger mul " + time/1000.0 + "s");

		System.out.println("-");
		time = System.currentTimeMillis();
		BigInt ma = new BigInt(a), mb = new BigInt(b);
		time = System.currentTimeMillis() - time;
		System.out.println("BigInt parsing " + time/1000.0 + "s");
		time = System.currentTimeMillis();
		for(int i = 0; i<loop; i++) ma.mul(mb);
		time = System.currentTimeMillis() - time;
		System.out.println("BigInt mul " + time/1000.0 + "s");

		System.out.println("-");
		time = System.currentTimeMillis();
		Apint apa = new Apint(new String(a)), apb = new Apint(new String(b));
		time = System.currentTimeMillis() - time;
		System.out.println("Apint parsing " + time/1000.0 + "s");
		time = System.currentTimeMillis();
		Apint apr = apa;
		for(int i = 0; i<loop; i++) apr = apr.multiply(apb);
		time = System.currentTimeMillis() - time;
		System.out.println("Apint mul " + time/1000.0 + "s");

		System.out.println("-");
		time = System.currentTimeMillis();
		LargeInteger lia = LargeInteger.valueOf(new String(a)), lib = LargeInteger.valueOf(new String(b));
		time = System.currentTimeMillis() - time;
		System.out.println("LargeInteger parsing " + time/1000.0 + "s");
		time = System.currentTimeMillis();
		LargeInteger lir = lia;
		for(int i = 0; i<loop; i++) lir = lir.times(lib);
		time = System.currentTimeMillis() - time;
		System.out.println("LargeInteger mul " + time/1000.0 + "s");

		System.out.println("-");
		time = System.currentTimeMillis();
		String ans = facit.toString();
		time = System.currentTimeMillis() - time;
		System.out.println("BigInteger toString() " + time/1000.0 + "s");

		time = System.currentTimeMillis();
		String ans2 = ma.toString();
		time = System.currentTimeMillis() - time;
		System.out.println("BigInt toString() " + time/1000.0 + "s");

		time = System.currentTimeMillis();
		String ans3 = apr.toString(true);
		time = System.currentTimeMillis() - time;
		System.out.println("Apint toString() " + time/1000.0 + "s");

		time = System.currentTimeMillis();
		String ans4 = lir.toString();
		time = System.currentTimeMillis() - time;
		System.out.println("LargeInteger toString() " + time/1000.0 + "s");

		System.out.println("-");
		System.out.println("BigInt Check: " + ans.equals(ans2) +" "+ans.length()+" "+ans2.length());
		System.out.println("Apint Check: " + ans.equals(ans3));
		System.out.println("LargeInteger Check: " + ans.equals(ans4));

		System.out.println("--------------------------------------\n");
	}
	/**
	* Aims to evaluate the speed of a single big multiplication.
	*/
	public static void runBigMul(final int nr)
	{
		runBigMul(nr, 300_000);
	}
	public static void runBigMul(final int nr, final int len)
	{
		System.out.printf("Run #%d of Big Multiplication\n", nr);
		final char[] a = new char[len], b = new char[len];
		getNum(a,len,1337); getNum(b,len,1990);
		System.out.println("Numbers generated (length: "+len+" decimal digits each)");

		System.out.println("-");
		time = System.currentTimeMillis();
		BigInteger aa = new BigInteger(new String(a)), bb = new BigInteger(new String(b));
		time = System.currentTimeMillis() - time;
		System.out.println("BigInteger parsing " + time/1000.0 + "s");
		time = System.currentTimeMillis();
		BigInteger facit = aa.multiply(bb);
		time = System.currentTimeMillis() - time;
		System.out.println("BigInteger mul " + time/1000.0 + "s");

		System.out.println("-");
		time = System.currentTimeMillis();
		BigInt ma = new BigInt(a), mb = new BigInt(b);
		time = System.currentTimeMillis() - time;
		System.out.println("BigInt parsing " + time/1000.0 + "s");
		time = System.currentTimeMillis();
		ma.mul(mb);
		time = System.currentTimeMillis() - time;
		System.out.println("BigInt mul " + time/1000.0 + "s");

		System.out.println("-");
		time = System.currentTimeMillis();
		Apint apa = new Apint(new String(a)), apb = new Apint(new String(b));
		time = System.currentTimeMillis() - time;
		System.out.println("Apint parsing " + time/1000.0 + "s");
		time = System.currentTimeMillis();
		Apint apr = apa.multiply(apb);
		time = System.currentTimeMillis() - time;
		System.out.println("Apint mul " + time/1000.0 + "s");

		System.out.println("-");
		time = System.currentTimeMillis();
		LargeInteger lia = LargeInteger.valueOf(new String(a)), lib = LargeInteger.valueOf(new String(b));
		time = System.currentTimeMillis() - time;
		System.out.println("LargeInteger parsing " + time/1000.0 + "s");
		time = System.currentTimeMillis();
		LargeInteger lir = lia.times(lib);
		time = System.currentTimeMillis() - time;
		System.out.println("LargeInteger mul " + time/1000.0 + "s");

		System.out.println("-");
		time = System.currentTimeMillis();
		String ans = facit.toString();
		time = System.currentTimeMillis() - time;
		System.out.println("BigInteger toString() " + time/1000.0 + "s");

		time = System.currentTimeMillis();
		String ans2 = ma.toString();
		time = System.currentTimeMillis() - time;
		System.out.println("BigInt toString() " + time/1000.0 + "s");

		time = System.currentTimeMillis();
		String ans3 = apr.toString(true);
		time = System.currentTimeMillis() - time;
		System.out.println("Apint toString() " + time/1000.0 + "s");

		time = System.currentTimeMillis();
		String ans4 = lir.toString();
		time = System.currentTimeMillis() - time;
		System.out.println("LargeInteger toString() " + time/1000.0 + "s");

		System.out.println("-");
		System.out.println("BigInt Check: " + ans.equals(ans2) +" "+ans.length()+" "+ans2.length());
		System.out.println("Apint Check: " + ans.equals(ans3));
		System.out.println("LargeInteger Check: " + ans.equals(ans4));

		System.out.println("--------------------------------------\n");
	}
	/**
	* Aims to evaluate the speed of many smaller multiplications.
	*/
	public static void runTinyMul(final int nr)
	{
		System.out.printf("Run #%d of many tiny multiplications\n", nr);
		final int len = 50_000;
		System.out.println("Factorial limit set to "+len);

		System.out.println("-");
		time = System.currentTimeMillis();
		BigInteger facit = BigInteger.ONE;
		for(int i = 1; i<=len; i++) facit = facit.multiply(BigInteger.valueOf(i));
		time = System.currentTimeMillis() - time;
		System.out.println("BigInteger tinymul " + time/1000.0 + "s");

		time = System.currentTimeMillis();
		BigInt fac = new BigInt(1);
		for(int i = 1; i<=len; i++) fac.umul(i);
		time = System.currentTimeMillis() - time;
		System.out.println("BigInt tinymul " + time/1000.0 + "s");

		time = System.currentTimeMillis();
		//Apint apfac = ApintMath.factorial(len);
		Apint apfac = new Apint(1);
		for(int i = 1; i<=len; i++) apfac = apfac.multiply(new Apint(i));
		time = System.currentTimeMillis() - time;
		System.out.println("Apint tinymul " + time/1000.0 + "s");

		time = System.currentTimeMillis();
		LargeInteger lifac = LargeInteger.ONE;
		for(int i = 1; i<=len; i++) lifac = lifac.times(i);
		time = System.currentTimeMillis() - time;
		System.out.println("LargeInteger tinymul " + time/1000.0 + "s");

		System.out.println("-");
		time = System.currentTimeMillis();
		String ans = facit.toString();
		time = System.currentTimeMillis() - time;
		System.out.println("BigInteger toString() " + time/1000.0 + "s");

		time = System.currentTimeMillis();
		String ans2 = fac.toString();
		time = System.currentTimeMillis() - time;
		System.out.println("BigInt toString() " + time/1000.0 + "s");

		time = System.currentTimeMillis();
		String ans3 = apfac.toString(true);
		time = System.currentTimeMillis() - time;
		System.out.println("Apint toString() " + time/1000.0 + "s");

		time = System.currentTimeMillis();
		String ans4 = lifac.toString();
		time = System.currentTimeMillis() - time;
		System.out.println("LargeInteger toString() " + time/1000.0 + "s");

		System.out.println("-");
		System.out.println("BigInt Check: " + ans.equals(ans2) +" "+ans.length()+" "+ans2.length());
		System.out.println("Apint Check: " + ans.equals(ans3));
		System.out.println("LargeInteger Check: " + ans.equals(ans4));

		System.out.println("--------------------------------------\n");
	}
	/**
	* Aims to evaluate the speed of general division.
	*/
	public static void runDiv(final int nr)
	{
		System.out.printf("Run #%d of Division\n", nr);
		final int len = 400_000, loop = 1000;
		final char[] a = new char[len], b = new char[len/loop];
		getNum(a,len,73311337); getNum(b,b.length,9911990);
		System.out.println("Numbers generated");

		System.out.println("-");
		time = System.currentTimeMillis();
		BigInteger aa = new BigInteger(new String(a)), bb = new BigInteger(new String(b));
		time = System.currentTimeMillis() - time;
		System.out.println("BigInteger parsing " + time/1000.0 + "s");
		time = System.currentTimeMillis();
		BigInteger facit = aa;
		for(int i = 0; i<loop; i++) facit = facit.divide(bb);
		time = System.currentTimeMillis() - time;
		System.out.println("BigInteger div " + time/1000.0 + "s");

		System.out.println("-");
		time = System.currentTimeMillis();
		BigInt ma = new BigInt(a), mb = new BigInt(b);
		time = System.currentTimeMillis() - time;
		System.out.println("BigInt parsing " + time/1000.0 + "s");
		time = System.currentTimeMillis();
		for(int i = 0; i<loop; i++) ma.div(mb);
		time = System.currentTimeMillis() - time;
		System.out.println("BigInt div " + time/1000.0 + "s");

		System.out.println("-");
		time = System.currentTimeMillis();
		Apint apa = new Apint(new String(a)), apb = new Apint(new String(b));
		time = System.currentTimeMillis() - time;
		System.out.println("Apint parsing " + time/1000.0 + "s");
		time = System.currentTimeMillis();
		Apint apr = apa;
		for(int i = 0; i<loop; i++) apr = apr.divide(apb);
		time = System.currentTimeMillis() - time;
		System.out.println("Apint div " + time/1000.0 + "s");

		System.out.println("-");
		time = System.currentTimeMillis();
		LargeInteger lia = LargeInteger.valueOf(new String(a)), lib = LargeInteger.valueOf(new String(b));
		time = System.currentTimeMillis() - time;
		System.out.println("LargeInteger parsing " + time/1000.0 + "s");
		time = System.currentTimeMillis();
		LargeInteger lir = lia;
		for(int i = 0; i<loop; i++) lir = lir.divide(lib);
		time = System.currentTimeMillis() - time;
		System.out.println("LargeInteger div " + time/1000.0 + "s");

		System.out.println("-");
		time = System.currentTimeMillis();
		String ans = facit.toString();
		time = System.currentTimeMillis() - time;
		System.out.println("BigInteger toString() " + time/1000.0 + "s");

		time = System.currentTimeMillis();
		String ans2 = ma.toString();
		time = System.currentTimeMillis() - time;
		System.out.println("BigInt toString() " + time/1000.0 + "s");

		time = System.currentTimeMillis();
		String ans3 = apr.toString(true);
		time = System.currentTimeMillis() - time;
		System.out.println("Apint toString() " + time/1000.0 + "s");

		time = System.currentTimeMillis();
		String ans4 = lir.toString();
		time = System.currentTimeMillis() - time;
		System.out.println("LargeInteger toString() " + time/1000.0 + "s");

		System.out.println("-");
		System.out.println("BigInt Check: " + ans.equals(ans2) +" "+ans.length()+" "+ans2.length());
		System.out.println("Apint Check: " + ans.equals(ans3));
		System.out.println("LargeInteger Check: " + ans.equals(ans4));

		System.out.println("--------------------------------------\n");
	}
	/**
	* Aims to evaluate the speed of a single big division.
	*/
	public static void runBigDiv(final int nr)
	{
		System.out.printf("Run #%d of Big Division\n", nr);
		final int ulen = 400_000, vlen = ulen/2;
		final char[] a = new char[ulen], b = new char[vlen];
		getNum(a,ulen,73311337); getNum(b,vlen,9911990);
		System.out.printf("Numbers generated (length: %d and %d decimal digits)\n", ulen, vlen);

		System.out.println("-");
		time = System.currentTimeMillis();
		BigInteger aa = new BigInteger(new String(a)), bb = new BigInteger(new String(b));
		time = System.currentTimeMillis() - time;
		System.out.println("BigInteger parsing " + time/1000.0 + "s");
		time = System.currentTimeMillis();
		BigInteger facit = aa.divide(bb);
		time = System.currentTimeMillis() - time;
		System.out.println("BigInteger div " + time/1000.0 + "s");

		System.out.println("-");
		time = System.currentTimeMillis();
		BigInt ma = new BigInt(a), mb = new BigInt(b);
		time = System.currentTimeMillis() - time;
		System.out.println("BigInt parsing " + time/1000.0 + "s");
		time = System.currentTimeMillis();
		ma.div(mb);
		time = System.currentTimeMillis() - time;
		System.out.println("BigInt div " + time/1000.0 + "s");

		System.out.println("-");
		time = System.currentTimeMillis();
		Apint apa = new Apint(new String(a)), apb = new Apint(new String(b));
		time = System.currentTimeMillis() - time;
		System.out.println("Apint parsing " + time/1000.0 + "s");
		time = System.currentTimeMillis();
		Apint apr = apa.divide(apb);
		time = System.currentTimeMillis() - time;
		System.out.println("Apint div " + time/1000.0 + "s");

		System.out.println("-");
		time = System.currentTimeMillis();
		LargeInteger lia = LargeInteger.valueOf(new String(a)), lib = LargeInteger.valueOf(new String(b));
		time = System.currentTimeMillis() - time;
		System.out.println("LargeInteger parsing " + time/1000.0 + "s");
		time = System.currentTimeMillis();
		LargeInteger lir = lia.divide(lib);
		time = System.currentTimeMillis() - time;
		System.out.println("LargeInteger div " + time/1000.0 + "s");

		System.out.println("-");
		time = System.currentTimeMillis();
		String ans = facit.toString();
		time = System.currentTimeMillis() - time;
		System.out.println("BigInteger toString() " + time/1000.0 + "s");

		time = System.currentTimeMillis();
		String ans2 = ma.toString();
		time = System.currentTimeMillis() - time;
		System.out.println("BigInt toString() " + time/1000.0 + "s");

		time = System.currentTimeMillis();
		String ans3 = apr.toString(true);
		time = System.currentTimeMillis() - time;
		System.out.println("Apint toString() " + time/1000.0 + "s");

		time = System.currentTimeMillis();
		String ans4 = lir.toString();
		time = System.currentTimeMillis() - time;
		System.out.println("LargeInteger toString() " + time/1000.0 + "s");

		System.out.println("-");
		System.out.println("BigInt Check: " + ans.equals(ans2) +" "+ans.length()+" "+ans2.length());
		System.out.println("Apint Check: " + ans.equals(ans3));
		System.out.println("LargeInteger Check: " + ans.equals(ans4));

		System.out.println("--------------------------------------\n");
	}
	/**
	* Aims to evaluate the speed of Karatsuba and Parallell Karatsuba.
	*/
	public static void compareKaratsuba(final int nr)
	{
		System.out.printf("Run #%d of Karatsuba comparison\n", nr);
		final int len = 400_000;
		final int[] a = new int[len], b = new int[len];
		getBigNum(a,len,1337); getBigNum(b,len,1990);
		System.out.printf("Big Numbers generated (approx. %d bits each)\n", len*32L);

		System.out.println("-");

		BigInt ma, mb;

		/*ma = new BigInt(1,a,len); mb = new BigInt(1,b,len);
		time = System.currentTimeMillis();
		ma.smallMul(mb);
		//final int[] ans1 = ma.dig;
		time = System.currentTimeMillis() - time;
		System.out.println("BigInt mul " + time/1000.0 + "s");*/

		ma = new BigInt(1,a,len); mb = new BigInt(1,b,len);
		time = System.currentTimeMillis();
		ma.karatsuba(mb);
		//final int[] ans1 = ma.dig;
		time = System.currentTimeMillis() - time;
		System.out.println("BigInt kmul " + time/1000.0 + "s");

		ma = new BigInt(1,a,len); mb = new BigInt(1,b,len);
		time = System.currentTimeMillis();
		ma.karatsuba(mb,true);
		//final int[] ans2 = ma.dig;
		time = System.currentTimeMillis() - time;
		System.out.println("BigInt pmul " + time/1000.0 + "s");

		System.out.println("--------------------------------------\n");
	}

	public static void main(String[] klein)
	{
		//runAdd(1); runAdd(2);
		//runSub(1); runSub(2);
		//runMul(1); runMul(2);
		//runTinyMul(1);
		//runBigMul(1); runBigMul(2);
		runBigMul(1, 500_000); runBigMul(2, 500_000);
		//runDiv(1); runDiv(2);
		//runBigDiv(1); runBigDiv(2);
		//compareKaratsuba(1); compareKaratsuba(2);
	}
}

/*
Run #1 of Division
Numbers generated
-
BigInteger parsing 10.739s
BigInteger div 9.936s
-
BigInt parsing 2.622s
BigInt div 7.558s
-
Apint parsing 0.491s
Apint div 297.022s
-
LargeInteger parsing 4.859s
LargeInteger div 3239.432s
-
BigInteger toString() 0.0s
BigInt toString() 0.0s
Apint toString() 0.001s
LargeInteger toString() 0.031s
-
BigInt Check: true 362 362
Apint Check: true
LargeInteger Check: true
--------------------------------------

Run #2 of Division
Numbers generated
-
BigInteger parsing 10.282s
BigInteger div 9.764s
-
BigInt parsing 2.736s
BigInt div 7.6s
-
Apint parsing 0.018s
Apint div 306.271s
-
LargeInteger parsing 4.49s
...


Run #1 of Big Division
Numbers generated (length: 400000 and 200000 decimal digits)
-
BigInteger parsing 13.776s
BigInteger div 3.3s
-
BigInt parsing 3.274s
BigInt div 2.714s
-
Apint parsing 0.473s
Apint div 1.682s
-
LargeInteger parsing 5.989s
LargeInteger div 6.307s
-
BigInteger toString() 16.286s
BigInt toString() 4.255s
Apint toString() 0.078s
LargeInteger toString() 13.505s
-
BigInt Check: true 200000 200000
Apint Check: true
LargeInteger Check: true
--------------------------------------

Run #2 of Big Division
Numbers generated (length: 400000 and 200000 decimal digits)
-
BigInteger parsing 12.406s
BigInteger div 3.647s
-
BigInt parsing 3.411s
BigInt div 2.536s
-
Apint parsing 0.027s
Apint div 0.563s
-
LargeInteger parsing 5.587s
LargeInteger div 5.335s
-
BigInteger toString() 15.874s
BigInt toString() 2.903s
Apint toString() 0.017s
LargeInteger toString() 13.298s
-
BigInt Check: true 200000 200000
Apint Check: true
LargeInteger Check: true
--------------------------------------
*/