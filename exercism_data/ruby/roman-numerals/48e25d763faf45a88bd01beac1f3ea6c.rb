class Fixnum
  ROMAN_DIGITS = [ ['', 'I', 'II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII', 'IX'],
                   ['', 'X', 'XX', 'XXX', 'XL', 'L', 'LX', 'LXX', 'LXXX', 'XC'],
                   ['', 'C', 'CC', 'CCC', 'CD', 'D', 'DC', 'DCC', 'DCCC', 'CM'],
                   ['', 'M', 'MM', 'MMM'] ]
  def to_roman
    digits = to_s
    roman = ''
    digits.chars.each_with_index { |d, i|  roman << ROMAN_DIGITS[digits.length-i-1][d.to_i] }
    roman
  end
end
