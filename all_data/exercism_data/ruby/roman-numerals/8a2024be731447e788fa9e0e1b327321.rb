class Fixnum
  @@roman_numerals = {
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
    number = self
    roman_numeral_string = ""

    @@roman_numerals.each do |key, roman_numeral|
      roman_numeral_string += roman_numeral * (number/key)
      number %= key
    end

    roman_numeral_string
  end
end
