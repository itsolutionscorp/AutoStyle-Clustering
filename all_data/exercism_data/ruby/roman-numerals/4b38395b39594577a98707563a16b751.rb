class Fixnum
  VALUES = {
    1000 => 'M',
    900 => 'CM',
    500 => 'D',
    400 => 'CD',
    100 => 'C',
    90 => 'XC',
    50 => 'L',
    40 => 'XL',
    10 => 'X',
    9 => 'IX',
    5 => 'V',
    4 => 'IV',
    1 => 'I'
  }

  def to_roman
    roman_numerals = ""
    remainder = self
    VALUES.each do |value, roman_numeral|
      quotient, remainder = remainder.divmod(value)
      quotient.times do roman_numerals << roman_numeral end
    end
    roman_numerals
  end
end
