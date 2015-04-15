# http://www.roman-numerals.org/chart100.html
module RomanNumerals
  ARABIC_TO_ROMAN = {
    1    => 'I',
    4    => 'IV',
    5    => 'V',
    9    => 'IX',
    10   => 'X',
    40   => 'XL',
    50   => 'L',
    90   => 'XC',
    100  => 'C',
    400  => 'CD',
    500  => 'D',
    900  => 'CM',
    1000 => 'M'
  }

  def to_roman
    total = 0
    roman_numerals = ''
    while total < self
      keys_below_number = ARABIC_TO_ROMAN.keys.select { |key| key <= self - total }
      total += keys_below_number.last
      roman_numerals << ARABIC_TO_ROMAN[keys_below_number.last]
    end
    roman_numerals
  end
end

class Integer
  include RomanNumerals
end
