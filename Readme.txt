To execute the code

javac main_prog.java
java main_prog

Details of the code

 For calculating Step 0 -> a^b -> higher_power function is used.
 For calculating Step 1 -> a^b mod n -> modular_higher_power function is used.
 For calculating z in Step 2 from 2^y*z -> two_factorize is used.


In Miller Rabin testing Step 0. if n=a^b then return composite.
Testing Step 1. If rnd^n-1 mod n != 1 then it's a composite number. where rnd is random no chosen from 2 to n-1.
If step 1 fails it goes for step 2 checking and call two factorize. We are returning z the remainder of (n-1)%2^y
If result is n-1 or 1 then the no is prime. Else we will increase the power of b0 by 2 and again check for the remainder.
Return 0 has been passed for composite number.
