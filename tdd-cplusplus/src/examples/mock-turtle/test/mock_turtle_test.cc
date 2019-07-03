#include "../mock_turtle.h"
#include "../painter.h"
#include "gtest/gtest.h"
#include "gmock/gmock.h"

using ::testing::AtLeast;
using ::testing::Return;

TEST(PainterTest, CanDrawSomething) {
  MockTurtle turtle;
  EXPECT_CALL(turtle, PenDown()).Times(AtLeast(1));

  Painter painter(&turtle);

  EXPECT_TRUE(painter.DrawCircle(0, 0, 10));


}


TEST(PainterTest, CanDrawSomething2) {
  MockTurtle turtle;

  EXPECT_CALL(turtle, GetX()).Times(AtLeast(2));
  ON_CALL(turtle, GetX()).WillByDefault(Return(1));
  EXPECT_EQ(turtle.GetX(),1);
  EXPECT_EQ(turtle.GetX(),1);
}
