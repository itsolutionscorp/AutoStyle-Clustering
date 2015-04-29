module RomanNumeral
  CONVERSIONS =    { 1 => 'I',
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

  def self.max_decimal_conversion_for(n)
    CONVERSIONS.keys.select { |decimal| decimal <= n }.max
  end

  def self.max_roman_conversion_for(n)
    CONVERSIONS[max_decimal_conversion_for(n)]
  end

  def self.roman_for(n)
    return '' if n == 0
    max_roman_conversion_for(n) + roman_for(n-max_decimal_conversion_for(n))
  end
end

class Integer
  def to_roman
    RomanNumeral.roman_for(self)
  end
end
