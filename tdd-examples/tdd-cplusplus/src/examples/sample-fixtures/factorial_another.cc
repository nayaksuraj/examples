#include "factorial_another.h"



// Returns n! (the factorial of n).  For negative n, n! is defined to be 1.
int FactorialAnother(int n) {
  int result = 1;
  for (int i = 1; i <= n; i++) {
    result *= i;
  }
  return result;
}
