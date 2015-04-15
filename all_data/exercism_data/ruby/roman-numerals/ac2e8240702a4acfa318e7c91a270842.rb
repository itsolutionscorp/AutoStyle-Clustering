class Fixnum

  def to_roman
    result = ""
    remainder = self
    result, remainder = Fixnum.roman_digit(remainder, "M", 1000, result )
    result, remainder = Fixnum.roman_digit(remainder, "CM", 900, result )
    result, remainder = Fixnum.roman_digit(remainder, "D", 500, result )
    result, remainder = Fixnum.roman_digit(remainder, "CD", 400, result )
    result, remainder = Fixnum.roman_digit(remainder, "C", 100, result )
    result, remainder = Fixnum.roman_digit(remainder, "XC", 90, result )
    result, remainder = Fixnum.roman_digit(remainder, "L", 50, result )
    result, remainder = Fixnum.roman_digit(remainder, "XL", 40, result )
    result, remainder = Fixnum.roman_digit(remainder, "X", 10, result )
    result, remainder = Fixnum.roman_digit(remainder, "IX", 9, result )
    result, remainder = Fixnum.roman_digit(remainder, "V", 5, result )
    result, remainder = Fixnum.roman_digit(remainder, "IV", 4, result )
    result, remainder = Fixnum.roman_digit(remainder, "I", 1, result )
    result
  end

  def self.roman_digit number, letter, value, stem
    result = stem
    n = number/value
    counter = number % value
    n.times {result = result + letter}
    return result, counter
  end

end
