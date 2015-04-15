class Fixnum

  ARABIC_TO_ROMAN_VALUES = {
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
    1 => "I"
  }

  def to_roman
    roman_value = ''
    arabic_value = self
    ARABIC_TO_ROMAN_VALUES.each_pair do |arabic, roman|
      while arabic_value >= arabic
        arabic_value -= arabic
        roman_value << roman
      end
    end
    roman_value
  end
end
