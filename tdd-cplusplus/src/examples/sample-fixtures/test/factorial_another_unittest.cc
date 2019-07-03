
#include "../factorial_another.h"

#include <limits.h>
#include <time.h>
#include "gtest/gtest.h"
#include <chrono>
#include <thread>


namespace {
// In this sample, we want to ensure that every test finishes within
// ~5 seconds.  If a test takes longer to run, we consider it a
// failure.
//
// We put the code for timing a test in a test fixture called
// "QuickTest".  QuickTest is intended to be the super fixture that
// other fixtures derive from, therefore there is no test case with
// the name "QuickTest".  This is OK.
//
// Later, we will derive multiple test fixtures from QuickTest.
class QuickTest : public testing::Test {

 protected:
  // Remember that SetUp() is run immediately before a test starts.
  // This is a good place to record the start time.
  void SetUp() override { start_time_ = time(nullptr); }

  // TearDown() is invoked immediately after a test finishes.  Here we
  // check if the test was too slow.
  void TearDown() override {
    // Gets the time when the test finishes
    const time_t end_time = time(nullptr);

    // Asserts that the test took no more than ~5 seconds.  Did you
    // know that you can use assertions in SetUp() and TearDown() as
    // well?
    EXPECT_TRUE(end_time - start_time_ <= 1) << "The test took too long.";
  }

  // The UTC time (in seconds) when the test starts
  time_t start_time_;
};


// We derive a fixture named FactorialAnotherTest from the QuickTest
// fixture.  All tests using this fixture will be automatically
// required to be quick.
class FactorialAnotherTest : public QuickTest {

  // Therefore the body is empty.
};


// Now we can write tests in the IntegerFunctionTest test case.

// Tests Factorial()
TEST_F(FactorialAnotherTest, FactorialAnother) {
  // Tests factorial of negative numbers.
  EXPECT_EQ(1, FactorialAnother(-5));
  EXPECT_EQ(1, FactorialAnother(-1));
  EXPECT_GT(FactorialAnother(-10), 0);

  std::this_thread::sleep_for(std::chrono::milliseconds(2000));

  // Tests factorial of 0.
  EXPECT_EQ(1, FactorialAnother(0));

  // Tests factorial of positive numbers.
  EXPECT_EQ(1, FactorialAnother(1));
  EXPECT_EQ(2, FactorialAnother(2));
  EXPECT_EQ(6, FactorialAnother(3));
  EXPECT_EQ(40320, FactorialAnother(8));
}
}
