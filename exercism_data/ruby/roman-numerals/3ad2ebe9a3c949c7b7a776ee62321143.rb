class Fixnum
  ROMANS_SINGLE_DIGITS = [
    '', 'I', 'II', 'III', 'IV', 'V',
    'VI', 'VII', 'VIII', 'IX'
  ]

  ROMANS_DOUBLE_DIGITS = [
    '', 'X', 'XX', 'XXX', 'XL', 'L',
    'LX', 'LXX', 'LXXX', 'XC'
  ]

  ROMANS_TRIPLE_DIGITS = [
    '', 'C', 'CC', 'CCC', 'CD', 'D',
    'DC', 'DCC', 'DCCC', 'CM'
  ]

  ROMANS_QUADRUPLE_DIGITS = [
    '', 'M', 'MM', 'MMM'
  ]

  def to_roman
    digits = self.to_s.split('').map(&:to_i)
    result = ''

    result += ROMANS_QUADRUPLE_DIGITS[digits[-4]] if digits[-4]
    result += ROMANS_TRIPLE_DIGITS[digits[-3]] if digits[-3]
    result += ROMANS_DOUBLE_DIGITS[digits[-2]] if digits[-2]
    result += ROMANS_SINGLE_DIGITS[digits[-1]]

    result
  end
end
