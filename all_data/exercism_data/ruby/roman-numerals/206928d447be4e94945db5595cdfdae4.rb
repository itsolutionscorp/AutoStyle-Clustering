module BaseNumeral
  BASE_NUMERALS =  { 1 => 'I',
                     4 => 'IV',
                     5 => 'V',
                     9 => 'IX',
                     10 => 'X',
                     40 => 'XL',
                     50 => 'L',
                     90 => 'XC',
                     100 => 'C',
                     400 => 'CD',
                     500 => 'D',
                     900 => 'CM',
                     1000 => 'M' }

  def self.max_decimal_for(n)
    BASE_NUMERALS.keys.select { |decimal| decimal <= n }.max
  end

  def self.max_roman_for(n)
    BASE_NUMERALS[self.max_decimal_for(n)]
  end
end

class Integer
  def to_roman
    remainder = self
    roman_numeral = ''
    while remainder > 0
      roman_numeral << BaseNumeral.max_roman_for(remainder)
      remainder -= BaseNumeral.max_decimal_for(remainder)
    end
    roman_numeral
  end
end
