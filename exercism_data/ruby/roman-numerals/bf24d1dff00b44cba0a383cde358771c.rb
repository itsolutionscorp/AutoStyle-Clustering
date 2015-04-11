NUMERALS = {
  1000 => 'M', 900 => 'CM',
  500 => 'D', 400 => 'CD',
  100 => 'C', 90 => 'XC',
  50 => 'L', 40 => 'XL',
  10 => 'X', 9 => 'IX',
  5 => 'V', 4 => 'IV',
  1 => 'I'
}

class Fixnum
  def to_roman
    num = self
    NUMERALS.reduce("") do | roman_numeral, (arabic_val, roman_val)|
      while num >= arabic_val
        num -= arabic_val
        roman_numeral << roman_val
      end
      roman_numeral
    end
  end
end
