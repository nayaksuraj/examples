#include "../prime-test/sample1.h"

// Returns n! (the factorial of n).  For negative n, n! is defined to be 1.
int Factorial(int n) {
  int result = 1;



  return result;
}

// Returns true iff n is a prime number.
bool IsPrime(int n) {
  // Trivial case 1: small numbers


  // Trivial case 2: even numbers


  // Now, we have that n is odd and n >= 3.

  // Try to divide n by every odd number i, starting from 3


  // n has no integer factor in the range (1, n), and thus is prime.
  return true;
}
