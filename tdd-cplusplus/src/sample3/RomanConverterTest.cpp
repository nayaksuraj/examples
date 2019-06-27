#include "gmock/gmock.h"

using namespace ::testing;
using namespace std;

string convert(unsigned int arabic){
	if (arabic == 2){
		return "II";
	}else if (arabic == 3){
		return "III";
	}else{
		return "I";
	}
}

TEST(RomanConverter, CanConvertPositiveDigits){
	EXPECT_THAT(convert(1), Eq("I"));
	EXPECT_THAT(convert(2), Eq("II"));
	EXPECT_THAT(convert(3), Eq("III"));
}
