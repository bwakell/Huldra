package org.huldra.math;

import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.*;

public class BasicTest
{
	static Random rnd = new Random();

	@Test
	public void constructorTest()
	{
		String s = "246313781983713469235139859013498018470170100003957203570275438387";
		String ans = new BigInt(s).toString();
		assertEquals("Error in toString()", s, ans);
		assertEquals("Error 4M", "4000000000", new BigInt("4000000000").toString());
		assertEquals("Error", "3928649759", new BigInt("3928649759").toString());
		BigInt me = new BigInt(s); me.umul(0);
		assertEquals("Zero string", "0", me.toString());
		me = new BigInt("0");
		assertEquals("Zero string2", "0", me.toString());
		//Add test case covering length-increase due to add in mulAdd().
	}

	@Test
	public void addTest()
	{
		String s = "246313781983713469235139859013498018470170100003957203570275438387";
		String t = "2374283475698324756873245832748";
		BigInteger facit = new BigInteger(s).add(new BigInteger(t));

		BigInt me = new BigInt(s); me.add(new BigInt(t));
		assertEquals("Add", facit.toString(), me.toString());

		me = new BigInt(t); me.add(new BigInt(s));
		assertEquals("Add2", facit.toString(), me.toString());

		facit = new BigInteger(s);
		facit = facit.add(facit);
		me.assign(s); me.add(me);
		assertEquals("Add3", facit.toString(), me.toString());

		facit = new BigInteger(t);
		facit = facit.add(facit);
		me.assign(t); me.add(me);
		assertEquals("Add4", facit.toString(), me.toString());

		me = new BigInt("0"); facit = BigInteger.ZERO;
		for(int i = 0; i<1337; i++)
		{
			long tmp = rnd.nextLong()&((1L<<32)-1);
			me.uadd((int)tmp); facit = facit.add(BigInteger.valueOf(tmp));
			assertEquals("For-loop "+i+": "+me+" "+facit+"\nAdded: "+tmp, facit.toString(), me.toString());
			tmp = rnd.nextLong()>>>1;
			me.uadd(tmp); facit = facit.add(BigInteger.valueOf(tmp));
			assertEquals("For-loop2 "+i+": "+me+" "+facit+"\nAdded: "+tmp, facit.toString(), me.toString());
		}
	}

	@Test
	public void subTest()
	{
		String s = "246313781983713469235139859013498018470170100003957203570275438387";
		String t = "2374283475698324756873245832748";
		BigInt me = new BigInt(s); me.sub(new BigInt(s));
		assertEquals("Sub to zero", "0", me.toString());
		me = new BigInt(t); me.sub(new BigInt(t));
		assertEquals("Sub2 to zero", "0", me.toString());
		me = new BigInt("1337"); me.usub(1337);
		assertEquals("Small sub", "0", me.toString());
		me = new BigInt("4000000000"); me.sub(new BigInt("2000000000"));
		assertEquals("Small sub", "2000000000", me.toString());

		BigInteger facit = new BigInteger(s).subtract(new BigInteger(t));
		me = new BigInt(s); me.sub(new BigInt(t));
		assertEquals("Sub", facit.toString(), me.toString());

		facit = new BigInteger(t).subtract(new BigInteger(s));
		me = new BigInt(t); BigInt tmp = new BigInt("-"+s);
		me.add(tmp);
		assertEquals("Sub2", facit.toString(), me.toString());

		me.umul(0);
		me.usub(1);
		assertEquals("From 0 to -1", "-1", me.toString());
		me.mul(-16);
		assertEquals("From -1 to 16", "16", me.toString());
		me.div(-4);
		assertEquals("From 16 to -4", "-4", me.toString());
	}

	@Test
	public void mulTest()
	{
		BigInt me = new BigInt("2000000000"); me.umul(3);
		assertEquals("Small", "6000000000", me.toString());
		me = new BigInt("4000000000"); me.mul(me);
		assertEquals("Two", "16000000000000000000", me.toString());

		String s = "246313781983713469235139859013498018470170100003957203570275438387";
		String t = "2374283475698324756873245832748";
		BigInteger facit = new BigInteger(s).multiply(new BigInteger(t));

		me = new BigInt(t); me.mul(new BigInt(s));
		assertEquals("Mul ", facit.toString(), me.toString());

		me.umul(0);
		me.uadd(1);
		assertEquals("0 to 1", "1", me.toString());
		me.mul(new BigInt(s));
		assertEquals("1 to s", s, me.toString());
		me.mul(new BigInt(t));
		assertEquals("Mul2", facit.toString(), me.toString());

		facit = new BigInteger(1, new byte[]{-1,-1,-1,-1, -1,-1,-1,-1, -1,-1,-1,-1, -1,-1,-1,-1});
		me = new BigInt(1, new int[]{-1, -1, -1, -1}, 4);
		BigInteger ulong = new BigInteger(1, new byte[]{-1,-1,-1,-1, -1,-1,-1,-1});
		for(int i = 0; i<256; i++)
		{
			facit = facit.multiply(ulong);
			me.umul(-1L);
			assertEquals("Long mul "+i, facit.toString(), me.toString());
		}
	}

	@Test
	public void divTest()
	{
		String s = "246313781983713469235139859013498018470170100003957203570275438387";
		String t = "2374283475698324756873245832748";
		BigInteger facit = new BigInteger(s).divide(BigInteger.valueOf(1337));
		BigInt me = new BigInt(s); me.udiv(1337);
		assertEquals("Div ", facit.toString(), me.toString());

		facit = new BigInteger(s+t+s).divide(BigInteger.valueOf((1L<<32)-1));
		me = new BigInt(s+t+s); me.udiv(-1);
		assertEquals("Div2 ", facit.toString(), me.toString());

		facit = new BigInteger(s).divide(new BigInteger(t));
		me = new BigInt(s); BigInt tmp = new BigInt(t);
		me.div(tmp);
		assertEquals("Div3 ", facit.toString(), me.toString());

		me.div(new BigInt(s));
		assertEquals("Should be 0", "0", me.toString());

		facit = new BigInteger(s).divide(new BigInteger("-"+t+t));
		me.assign(s); tmp.assign("-"+t+t); me.div(tmp);
		assertEquals("Div4 ", facit.toString(), me.toString());

		s = "253187224242823454860064468797249161593623134834254603067018";
		t = "434771785074759645588146668555";
		facit = new BigInteger(s).divide(new BigInteger(t));
		me = new BigInt(s); me.div(new BigInt(t));
		assertEquals("Div5 ", facit.toString(), me.toString());

		final int[] m = {2, 2, 2, 2}, n = {1, 2, 2, 2};
		final int[][] u = {{0xfffe0000,0x8000}, {0x00000003,0x8000}, {0,0x7fff8000}, {0xfffe0000,0x80000000}};
		final int[][] v = {{0x8000ffff}, {0x00000001,0x2000}, {1,0x8000}, {0xffff,0x8000}};
		final int[][] q = {{0xffff}, {0x0003}, {0xfffe}, {0xffff}};
		final int[][] r = {{0x7fffffff}, {0,0x2000}, {0xffff0002,0x7fff}, {0xffffffff,0x7fff}};
		for(int i = 0; i<4; i++)
		{
			BigInt a = new BigInt(1, u[i], m[i]), b = new BigInt(1, v[i], n[i]);
			BigInt rem = a.divRem(b);
			assertEquals("Hack div "+i, new BigInt(1,q[i],q[i].length).toString(), a.toString());
			assertEquals("Hack rem "+i, new BigInt(1,r[i],r[i].length).toString(), rem.toString());
		}

		s = "170141183460469231750134047781003722752";
		t = "39614081257132168801066942463";
		me = new BigInt(s); //me = new BigInt(1, new int[]{0,0xfffffffe,0,0x80000000}, 4);
		tmp = new BigInt(t); //tmp = new BigInt(1, new int[]{0xffffffff,0,0x80000000}, 3);
		BigInt rr = me.divRem(tmp);
		facit = new BigInteger(s).divide(new BigInteger(t));
		assertEquals("Div shift-32 ", facit.toString(), me.toString());
		facit = new BigInteger(s).remainder(new BigInteger(t));
		assertEquals("Rem shift-32 ", facit.toString(), rr.toString());

		me = new BigInt(1, new int[]{0,0,0x80000000,0x7fffffff}, 4);
		tmp = new BigInt(1, new int[]{1,0,0x80000000}, 3);
		BigInteger[] ans = new BigInteger(me.toString()).divideAndRemainder(new BigInteger(tmp.toString()));
		rr = me.divRem(tmp);
		assertEquals("Div addback ", ans[0].toString(), me.toString());
		assertEquals("Rem addback ", ans[1].toString(), rr.toString());

		me = new BigInt(1, new int[]{0x0003,0x0000,0x80000000}, 3);
		tmp = new BigInt(1, new int[]{0x0001,0x0000,0x20000000}, 3);
		ans = new BigInteger(me.toString()).divideAndRemainder(new BigInteger(tmp.toString()));
		rr = me.divRem(tmp);
		assertEquals("Div addback2 ", ans[0].toString(), me.toString());
		assertEquals("Rem addback2 ", ans[1].toString(), rr.toString());

		me = new BigInt(1, new int[]{0x0000,0xfffffffe,0x80000000}, 3);
		tmp = new BigInt(1, new int[]{0xffffffff,0x80000000}, 2);
		ans = new BigInteger(me.toString()).divideAndRemainder(new BigInteger(tmp.toString()));
		rr = me.divRem(tmp);
		assertEquals("Div qhat=b+1 ", ans[0].toString(), me.toString());
		assertEquals("Rem qhat=b+1 ", ans[1].toString(), rr.toString());
	}

	@Test
	public void remTest()
	{
		String s = "246313781983713469235139859013498018470170100003957203570275438387";
		String t = "2374283475698324756873245832748";
		BigInteger facit = new BigInteger(s).remainder(BigInteger.valueOf(1337));
		BigInt me = new BigInt(s); me.urem(1337);
		assertEquals("Rem ", facit.toString(), me.toString());

		facit = new BigInteger(s+t+s).remainder(BigInteger.valueOf((1L<<32)-1));
		me = new BigInt(s+t+s); me.urem(-1);
		assertEquals("Rem2 ", facit.toString(), me.toString());

		facit = new BigInteger(s).remainder(new BigInteger(t));
		me = new BigInt(s); BigInt tmp = new BigInt(t);
		me.rem(tmp);
		assertEquals("Rem3 ", facit.toString(), me.toString());

		me.rem(me);
		assertEquals("Should be 0", "0", me.toString());

		facit = new BigInteger(s).remainder(new BigInteger("-"+t+t));
		me.assign(s); tmp.assign("-"+t+t); me.rem(tmp);
		assertEquals("Rem4 ", facit.toString(), me.toString());
	}

	@Test
	public void longDivTest() //Division test using long as parameter.
	{
		for(int i = 0; i<100; i++)
		{//System.err.println(i+" divs");
			final char[] s = getRndNumber(1+i*10);
			BigInteger facit = new BigInteger(new String(s));
			final BigInt dividend = new BigInt(s);
			while(!dividend.isZero())
			{
				final long d = rnd.nextLong(); if(d==0) continue;
				final byte[] div = new byte[8]; long tmp = d;
				for(int j = 7; j>=0; j--, tmp>>>=8) div[j] = (byte)(tmp&0xFF);
				BigInteger prv = facit;
				facit = facit.divide(new BigInteger(1,div));
				dividend.udiv(d);
				assertEquals(""+prv+"/"+d+"", facit.toString(), dividend.toString());
			}
		}
	}

	private char[] getRndNumber(final int len)
	{
		final char[] num = new char[len];
		num[0] = (char)('1'+rnd.nextInt(9));
		for(int i = 1; i<len; i++) num[i] = (char)('0'+rnd.nextInt(10));
		return num;
	}

	@Test
	public void testLongCastInMul()
	{
		BigInt a = new BigInt("1000000000000000");
		BigInt b = new BigInt("1000000000000000");
		a.mul(b);
		assertEquals("10^15 * 10^15", "1000000000000000000000000000000", a.toString());
	}

	@Test
	public void testLongZeroAdd()
	{
		BigInt a = new BigInt(0);
		a.add(0L);
		assertEquals("add(0L)", true, a.isZero());
		a.uadd(0L);
		assertEquals("uadd(0L)", true, a.isZero());
		a.add(-1L);
		a.add(2L);
		a.add(-1L);
		assertEquals("-1L + 2L + -1L = 0", true, a.isZero());
		a.usub(7L);
		a.sub(-8L);
		assertEquals("-7L - -8L != 0", false, a.isZero());
		a.sub(1L);
		assertEquals("1 - 1L = 0", true, a.isZero());
	}

	@Test
	public void testDivAndRem()
	{
		// Check divRem
		{
			BigInt p = new BigInt(104608886616216589L), q = new BigInt(104608886616125069L);
			assertEquals("divRem", "91520", p.divRem(q).toString());
			assertEquals("divRem", "1", p.toString());
			assertEquals("divRem", "104608886616125069", q.toString());
		}
		// Check div
		{
			BigInt p = new BigInt(104608886616216589L), q = new BigInt(104608886616125069L);
			p.div(q);
			assertEquals("div", "1", p.toString());
			assertEquals("div", "104608886616125069", q.toString());
		}
		// Check rem
		{
			BigInt p = new BigInt(104608886616216589L), q = new BigInt(104608886616125069L);
			p.rem(q);
			assertEquals("rem", "91520", p.toString());
			assertEquals("rem", "104608886616125069", q.toString());
		}
		// Check udiv
		{
			BigInt p = new BigInt(104608886616216589L);
			long r = p.udiv(104608886616125069L);
			assertEquals("udiv", 91520L, r);
			assertEquals("udiv", "1", p.toString());
		}
	}

	@Test
	public void testBitShifts()
	{
		BigInt a = new BigInt("45982486592486793248673294867398579368598675986739851099");
		BigInt b = new BigInt("45982486592486793248673294867398579368598675986739851099");
		a.shiftLeft(3673);
		a.shiftRight(3673);
		assertEquals("Left+Right shift", b.toString(), a.toString());
	}

	@Test
	public void testSetClearFlipTestBit()
	{
		BigInt a = new BigInt(1);
		a.shiftLeft(1337);
		BigInt b = new BigInt(0);
		b.setBit(1337);
		assertEquals("Set bit", a.toString(), b.toString());
		assertEquals("Test bit", true, a.testBit(1337));
		assertEquals("Test bit", false, a.testBit(1336));
		b.clearBit(1337);
		assertEquals("Clear bit", true, b.isZero());
		assertEquals("Test bit", false, b.testBit(1337));
		b.flipBit(1337);
		assertEquals("Flip bit", a.toString(), b.toString());
		b.flipBit(1337);
		assertEquals("Flip bit", true, b.isZero());

		b = new BigInt("24973592847598349867938576938752986459872649249832748");
		BigInteger facit = new BigInteger("24973592847598349867938576938752986459872649249832748");
		b.flipBit(77);
		facit = facit.flipBit(77);
		assertEquals("Flip bit", facit.toString(), b.toString());
		b.flipBit(0);
		facit = facit.flipBit(0);
		assertEquals("Flip bit", facit.toString(), b.toString());
		b.flipBit(31);
		facit = facit.flipBit(31);
		assertEquals("Flip bit", facit.toString(), b.toString());
		b.flipBit(32);
		facit = facit.flipBit(32);
		assertEquals("Flip bit", facit.toString(), b.toString());
	}

	@Test
	public void testLongAdd()
	{
		BigInt a = new BigInt(0);
		a.add(-1L);
		assertEquals("Long add", "-1", a.toString());
		a.assign(1L<<40);
		a.assign(0);
		a.add(-1L);
		assertEquals("Long add", "-1", a.toString());
	}
}