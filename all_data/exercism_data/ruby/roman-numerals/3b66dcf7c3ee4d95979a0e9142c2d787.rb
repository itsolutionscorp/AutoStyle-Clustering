class Fixnum
  ROMAN_NUMERALS = {
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
    result = ''
    number = self
    ROMAN_NUMERALS.keys.each do |divisor|
      quotient, modulus = number.divmod(divisor)
      result << ROMAN_NUMERALS[divisor] * quotient
      number = modulus
    end
    result
  end
end
