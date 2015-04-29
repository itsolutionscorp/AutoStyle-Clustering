class Fixnum
  ROMAN_EQUIVS = {
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
    num = self
    ROMAN_EQUIVS.each.map { |value, letter|
      letter_reps = num / value
      num -= letter_reps * value
      letter * letter_reps
    }.compact.join
  end
end
