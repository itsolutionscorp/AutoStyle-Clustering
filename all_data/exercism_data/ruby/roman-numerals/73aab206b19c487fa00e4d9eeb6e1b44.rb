class Integer
  ROMAN_NUMERALS = { 1000 => "M", 900 => "CM", 500 => "D", 400 => "CD", 100 => "C", 90 => "XC", 50 => "L", 40 => "XL", 10 => "X", 9 => "IX", 5 => "V", 4 => "IV", 1 => "I" }

  def to_roman
    roman_result = ''
    n = self
    ROMAN_NUMERALS.keys.each do |decimal|
      while n >= decimal
        n -= decimal
        roman_result += ROMAN_NUMERALS[decimal]
      end
    end
    roman_result
  end
end
