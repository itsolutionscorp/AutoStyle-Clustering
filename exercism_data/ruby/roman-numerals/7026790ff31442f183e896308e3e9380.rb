module Roman

  ROMAN_NUMERALS = { 1000 => "M", 900 => "CM", 500 => "D", 400 => "CD", 100 => "C", 90 => "XC", 50 => "L", 40 => "XL", 10 => "X", 9 => "IX", 5 => "V", 4 => "IV", 1 => "I"}

  def to_roman
    value = self
    roman_number = ""
    ROMAN_NUMERALS.each do |number, letter|
      while (value >= number)
        roman_number << letter
        value -= number
      end
    end
    roman_number
  end
end

class Fixnum
  include Roman
end
