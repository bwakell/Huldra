# Huldra
A project to provide efficient Java primitives, mainly pertaining arbitrary-precision integer arithmetic.
Let's smash the pathetic BigInteger class!
(And let's learn how to code a proper bignum class. As of version 0.7 the BigInt class contains the basic operations, addition, subtraction, multiplication and division, making it a suitable entry point as it is not cluttered with more advanced functions.)

Below follows a comparison of the Huldra project's BigInt class with the [http://docs.oracle.com/javase/7/docs/api/](Java library's) [http://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html](BigInteger) class, [http://www.apfloat.org/apfloat_java/](Apfloat's) [http://www.apfloat.org/apfloat_java/docs/org/apfloat/Apint.html](Apint) class, and [http://jscience.org/](JScience's) [http://jscience.org/api/org/jscience/mathematics/number/LargeInteger.html](LargeInteger) class. This somewhat simple comparison is done using the Benchmark.java code in the benchmark folder using Java 7 and my shitty computer (1.65 Ghz Dual Core and 6GB RAM).

\--------------------------------------

Run #1 of Addition
Numbers generated

BigInteger parsing 1.392s
BigInteger add 6.425s

BigInt parsing 0.365s
BigInt add 3.033s

Apint parsing 0.471s
Apint add 27.956s

LargeInteger parsing 0.97s
LargeInteger add 6.495s

BigInteger toString() 4.006s
BigInt toString() 1.439s
Apint toString() 0.067s
LargeInteger toString() 3.496s

BigInt Check: true 100005 100005
Apint Check: true
LargeInteger Check: true

\--------------------------------------

Run #2 of Addition
Numbers generated

BigInteger parsing 1.255s
BigInteger add 6.394s

BigInt parsing 0.32s
BigInt add 3.006s

Apint parsing 0.011s
Apint add 27.279s

LargeInteger parsing 0.576s
LargeInteger add 6.322s

BigInteger toString() 3.795s
BigInt toString() 0.78s
Apint toString() 0.009s
LargeInteger toString() 3.353s

BigInt Check: true 100005 100005
Apint Check: true
LargeInteger Check: true

\--------------------------------------

Run #1 of Subtraction
Numbers generated

BigInteger parsing 1.072s
BigInteger sub 5.829s

BigInt parsing 0.299s
BigInt sub 2.392s

Apint parsing 0.473s
Apint sub 24.677s

LargeInteger parsing 0.845s
LargeInteger sub 6.101s

BigInteger toString() 3.989s
BigInt toString() 1.43s
Apint toString() 0.067s
LargeInteger toString() 3.494s

BigInt Check: true 100000 100000
Apint Check: true
LargeInteger Check: true

\--------------------------------------

Run #2 of Subtraction
Numbers generated

BigInteger parsing 0.961s
BigInteger sub 5.618s

BigInt parsing 0.241s
BigInt sub 2.243s

Apint parsing 0.01s
Apint sub 24.35s

LargeInteger parsing 0.467s
LargeInteger sub 6.026s

BigInteger toString() 3.775s
BigInt toString() 0.78s
Apint toString() 0.01s
LargeInteger toString() 3.368s

BigInt Check: true 100000 100000
Apint Check: true
LargeInteger Check: true

\--------------------------------------

Run #1 of Multiplication
Numbers generated

BigInteger parsing 0.007s
BigInteger mul 2.659s

BigInt parsing 0.01s
BigInt mul 2.31s

Apint parsing 0.342s
Apint mul 40.825s

LargeInteger parsing 0.34s
LargeInteger mul 3.331s

BigInteger toString() 33.61s
BigInt toString() 8.387s
Apint toString() 0.082s
LargeInteger toString() 30.197s

BigInt Check: true 299432 299432
Apint Check: true
LargeInteger Check: true

\--------------------------------------

Run #2 of Multiplication
Numbers generated

BigInteger parsing 0.002s
BigInteger mul 2.676s

BigInt parsing 0.001s
BigInt mul 2.259s

Apint parsing 0.003s
Apint mul 40.433s

LargeInteger parsing 0.001s
LargeInteger mul 3.176s

BigInteger toString() 33.061s
BigInt toString() 6.357s
Apint toString() 0.026s
LargeInteger toString() 29.826s

BigInt Check: true 299432 299432
Apint Check: true
LargeInteger Check: true

\--------------------------------------

Run #1 of many tiny multiplications
Factorial limit set to 50000

BigInteger tinymul 10.564s
BigInt tinymul 1.572s
Apint tinymul 37.248s
LargeInteger tinymul 7.353s

BigInteger toString() 17.365s
BigInt toString() 5.85s
Apint toString() 0.081s
LargeInteger toString() 15.696s

BigInt Check: true 213237 213237
Apint Check: true
LargeInteger Check: true

\--------------------------------------

Run #2 of many tiny multiplications
Factorial limit set to 50000

BigInteger tinymul 10.683s
BigInt tinymul 1.468s
Apint tinymul 35.535s
LargeInteger tinymul 6.879s

BigInteger toString() 16.912s
BigInt toString() 4.614s
Apint toString() 0.029s
LargeInteger toString() 15.116s

BigInt Check: true 213237 213237
Apint Check: true
LargeInteger Check: true

\--------------------------------------

Run #1 of Big Multiplication
Numbers generated (length: 300000 decimal digits each)

BigInteger parsing 12.177s
BigInteger mul 4.392s

BigInt parsing 3.027s
BigInt mul 0.659s

Apint parsing 0.549s
Apint mul 0.91s

LargeInteger parsing 5.479s
LargeInteger mul 1.441s

BigInteger toString() 134.003s
BigInt toString() 28.98s
Apint toString() 0.103s
LargeInteger toString() 120.294s

BigInt Check: true 599999 599999
Apint Check: true
LargeInteger Check: true

\--------------------------------------

Run #2 of Big Multiplication
Numbers generated (length: 300000 decimal digits each)

BigInteger parsing 11.696s
BigInteger mul 4.435s

BigInt parsing 3.06s
BigInt mul 0.468s

Apint parsing 0.027s
Apint mul 0.252s

LargeInteger parsing 5.055s
LargeInteger mul 0.495s

BigInteger toString() 133.728s
BigInt toString() 25.477s
Apint toString() 0.05s
LargeInteger toString() 119.81s

BigInt Check: true 599999 599999
Apint Check: true
LargeInteger Check: true

\--------------------------------------

Run #1 of Big Multiplication
Numbers generated (length: 500000 decimal digits each)

BigInteger parsing 34.19s
BigInteger mul 12.115s

BigInt parsing 8.385s
BigInt mul 0.873s

Apint parsing 0.552s
Apint mul 0.755s

LargeInteger parsing 14.387s
LargeInteger mul 2.265s

BigInteger toString() 378.415s
BigInt toString() 75.228s
Apint toString() 0.149s
LargeInteger toString() 333.47s

BigInt Check: true 999999 999999
Apint Check: true
LargeInteger Check: true

\--------------------------------------

Run #2 of Big Multiplication
Numbers generated (length: 500000 decimal digits each)

BigInteger parsing 31.602s
BigInteger mul 12.332s

BigInt parsing 8.497s
BigInt mul 0.655s

Apint parsing 0.049s
Apint mul 0.289s

LargeInteger parsing 14.054s
LargeInteger mul 1.266s

BigInteger toString() 377.726s
BigInt toString() 69.578s
Apint toString() 0.074s
LargeInteger toString() 332.906s

BigInt Check: true 999999 999999
Apint Check: true
LargeInteger Check: true

\--------------------------------------

Run #1 of Division
Numbers generated

BigInteger parsing 10.739s
BigInteger div 9.936s

BigInt parsing 2.622s
BigInt div 7.558s

Apint parsing 0.491s
Apint div 297.022s

LargeInteger parsing 4.859s
LargeInteger div 3239.432s

BigInteger toString() 0.0s
BigInt toString() 0.0s
Apint toString() 0.001s
LargeInteger toString() 0.031s

BigInt Check: true 362 362
Apint Check: true
LargeInteger Check: true

\--------------------------------------

Run #2 of Division
Numbers generated

BigInteger parsing 10.282s
BigInteger div 9.764s

BigInt parsing 2.736s
BigInt div 7.6s

Apint parsing 0.018s
Apint div 306.271s

LargeInteger parsing 4.49s
... This takes too much time so let's just give up...

\--------------------------------------

Run #1 of Big Division
Numbers generated (length: 400000 and 200000 decimal digits)

BigInteger parsing 13.776s
BigInteger div 3.3s

BigInt parsing 3.274s
BigInt div 2.714s

Apint parsing 0.473s
Apint div 1.682s

LargeInteger parsing 5.989s
LargeInteger div 6.307s

BigInteger toString() 16.286s
BigInt toString() 4.255s
Apint toString() 0.078s
LargeInteger toString() 13.505s

BigInt Check: true 200000 200000
Apint Check: true
LargeInteger Check: true

\--------------------------------------

Run #2 of Big Division
Numbers generated (length: 400000 and 200000 decimal digits)

BigInteger parsing 12.406s
BigInteger div 3.647s

BigInt parsing 3.411s
BigInt div 2.536s

Apint parsing 0.027s
Apint div 0.563s

LargeInteger parsing 5.587s
LargeInteger div 5.335s

BigInteger toString() 15.874s
BigInt toString() 2.903s
Apint toString() 0.017s
LargeInteger toString() 13.298s

BigInt Check: true 200000 200000
Apint Check: true
LargeInteger Check: true

\--------------------------------------
