class Fixnum
  ROMAN_VALUES = {
    1 => 'I',
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
    1000 => 'M',
  }

  def to_roman
    return '' if self.zero?
    value = ROMAN_VALUES.keys.select {|num| num <= self}.max
    ROMAN_VALUES[value] + (self - value).to_roman
  end
end
