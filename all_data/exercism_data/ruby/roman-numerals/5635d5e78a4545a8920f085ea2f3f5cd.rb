class Fixnum
  ROMAN_NUMERALS = {
    1 => 'I',
    2 => 'II',
    3 => 'III',
    4 => 'IV',
    5 => 'V',
    6 => 'VI',
    7 => 'VII',
    8 => 'VIII',
    9 => 'IX',
    10 => 'X',
    20 => 'XX',
    30 => 'XXX',
    40 => 'XL',
    50 => 'L',
    60 => 'LX',
    70 => 'LXX',
    80 => 'LXXX',
    90 => 'XC',
    100 => 'C',
    200 => 'CC',
    300 => 'CCC',
    400 => 'CD',
    500 => 'D',
    600 => 'DC',
    700 => 'DCC',
    800 => 'DCCC',
    900 => 'CM',
    1000 => 'M'
  }

  def to_roman
    return ROMAN_NUMERALS[self] if ROMAN_NUMERALS[self]
    digits = []
    if self >= 100
      hundreds, remainder = self.divmod(100)
      tens, ones = remainder.divmod(10)
      digits.concat([hundreds * 100, tens * 10, ones])
    else
      tens, ones = self.divmod(10)
      digits.concat([tens * 10, ones])
    end
    roman = digits.map { |digit| ROMAN_NUMERALS[digit] }.join
    if self.div(1000) > 1
      roman = ('M' * (self / 1000)) + roman
    end
    roman
  end
end
