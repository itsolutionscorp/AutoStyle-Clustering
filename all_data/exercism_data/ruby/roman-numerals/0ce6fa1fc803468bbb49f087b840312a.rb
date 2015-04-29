module Romanize
  # Map Roman numerals to their integer values
  ROMAN_NUMERAL_TO_INTEGER = [
    ["M"  , 1000],
    ["CM" , 900],
    ["D"  , 500],
    ["CD" , 400],
    ["C"  , 100],
    ["XC" , 90],
    ["L"  , 50],
    ["XL" , 40],
    ["X"  , 10],
    ["IX" , 9],
    ["V"  , 5],
    ["IV" , 4],
    ["I"  , 1]
  ]

  def self.to_roman(number)
    conversion = ""

    ROMAN_NUMERAL_TO_INTEGER.each do |roman_numeral, integer_value|
      roman_numeral_count, number = number.divmod(integer_value)
      conversion << (roman_numeral * roman_numeral_count)
    end

    conversion
  end
end

class Fixnum
  def to_roman
    Romanize.to_roman(self)
  end
end
