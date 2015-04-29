class Fixnum
  ROMAN_DIGITS = [ ['', 'I', 'II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII', 'IX'],
                   ['', 'X', 'XX', 'XXX', 'XL', 'L', 'LX', 'LXX', 'LXXX', 'XC'],
                   ['', 'C', 'CC', 'CCC', 'CD', 'D', 'DC', 'DCC', 'DCCC', 'CM'],
                   ['', 'M', 'MM', 'MMM'] ]
  def to_roman
    digits = to_s
    digits.chars.each_with_index.map { |d, i|  ROMAN_DIGITS[digits.length-i-1][d.to_i] }.join
  end
end
