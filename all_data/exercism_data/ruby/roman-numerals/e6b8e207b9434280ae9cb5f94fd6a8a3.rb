class Fixnum
  def to_roman
    Roman.to_roman(self)
  end
end

class Roman
  NUMERAL_TO_DECIMAL = {
    'M' => 1000,
    'D' => 500,
    'C' => 100,
    'L' => 50,
    'X' => 10,
    'V' => 5,
    'I' => 1
  }

  SUBSTITUTIONS = {
    'DCCCC' => 'CM',
    'CCCC'  => 'CD',
    'LXXXX' => 'XC',
    'XXXX'  => 'XL',
    'VIIII' => 'IX',
    'IIII'  => 'IV'
  }

  def self.to_roman(decimal_number)
    shrink_with_substitutions(naive_roman_numerals(decimal_number))
  end

  def self.shrink_with_substitutions(roman_number)
    shortened_roman_number = roman_number
    SUBSTITUTIONS.each do |pattern, substitution|
      shortened_roman_number = shortened_roman_number.gsub(pattern, substitution)
    end
    shortened_roman_number
  end

  def self.naive_roman_numerals(decimal_number)
    roman_numerals = ''
    decimal = decimal_number
    NUMERAL_TO_DECIMAL.each do |roman_numeral, decimal_value|
      while (decimal >= decimal_value)
        roman_numerals += roman_numeral
        decimal -= decimal_value
      end
    end
    roman_numerals
  end
end
