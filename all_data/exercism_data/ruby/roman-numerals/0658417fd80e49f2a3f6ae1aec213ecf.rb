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
    remainder = value
    VALUE_TO_ROMAN.each_with_object("") do |(denom, numeral), roman|
      roman << numeral * (remainder / denom)
      remainder = remainder % denom
    end
  end
end

class Fixnum
  def to_roman
    Romanizer.to_roman(self)
  end
end
