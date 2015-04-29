class Fixnum

  def to_roman
    NumberConverter.to_roman(self)
  end

end

module NumberConverter
  
  extend self

  ARABIC_TO_ROMAN_MAPPING = {
    1000 => "M",
    900 => "CM",
    500 => "D",
    400 => "CD",
    100 => "C",
    90 => "XC",
    50 => "L",
    40 => "XL",
    10 => "X",
    9 => "IX",
    5 => "V",
    4 => "IV",
    3 => "III",
    2 => "II",
    1 => "I"
  }

  def to_roman(arabic_numeral)
    roman_numeral = ""
    ARABIC_TO_ROMAN_MAPPING.each_pair do |arabic, roman|
      while arabic_numeral >= arabic
        arabic_numeral -= arabic
        roman_numeral << roman
      end
    end
    roman_numeral
  end

end
