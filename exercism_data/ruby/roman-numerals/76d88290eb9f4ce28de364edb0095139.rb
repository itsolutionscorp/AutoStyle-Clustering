class Fixnum
  ROMAN_NUMBERS_MAP = {
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

  def to_roman(number = self, return_string = '')
    ROMAN_NUMBERS_MAP.keys.each do |map_value_divisor|
      quotient, remainder = number.divmod(map_value_divisor)
      return_string << ROMAN_NUMBERS_MAP[map_value_divisor] * quotient
      return to_roman(remainder, return_string) if quotient > 0
    end
    return_string
  end
end


# roman_numeral = 99.to_roman
# puts roman_numeral
