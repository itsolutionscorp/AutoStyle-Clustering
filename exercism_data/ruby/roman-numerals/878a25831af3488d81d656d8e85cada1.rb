class Fixnum
  ROMAN_NUMS = {
    "M"   => 1000,
    "CM"  => 900,
    "D"   => 500,
    "CD"  => 400,
    "C"   => 100,
    "XC"  => 90,
    "L"   => 50,
    "XL"  => 40,
    "X"   => 10,
    "IX"  => 9,
    "V"   => 5,
    "IV"  => 4,
    "I"   => 1
  }

  def to_roman
    remainder = self
    roman_numeral = ''
    ROMAN_NUMS.each do |letter, value|
      times = remainder / value
      remainder = remainder % value
      roman_numeral << letter * times
    end
    roman_numeral
  end
end
