class Integer
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
    remainder = self
    roman_output = ''
    ROMAN_NUMERALS.each do |k, v|
      digit_count, remainder = remainder.divmod k
      digit_count.times { roman_output += v }
    end
    roman_output
  end
end
