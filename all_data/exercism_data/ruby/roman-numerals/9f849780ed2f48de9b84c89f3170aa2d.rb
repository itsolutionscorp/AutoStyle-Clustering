module Romanizer
  VALUE_TO_ROMAN = {
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
    1 => "I",
  }

  def self.to_roman(value)
    _zero, roman = VALUE_TO_ROMAN.reduce([value, ""]) do |value_pair, denom_pair|
        remainder, roman = value_pair
        denomination, numeral = denom_pair
        roman << numeral * (remainder / denomination)
        [remainder % denomination, roman]
    end
    roman
  end
end

class Fixnum
  def to_roman
    Romanizer.to_roman(self)
  end
end
