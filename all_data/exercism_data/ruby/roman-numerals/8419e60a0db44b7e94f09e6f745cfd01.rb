class Integer
  ROMAN_TRANSLATION = {
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
    1000 => 'M'
  }

  def to_roman
    num = self
    ints = ROMAN_TRANSLATION.keys.select { |x| x <= num }
    roman_numerals = ""
    until num == 0
      largest_int_smaller_than_num = ints.last
      if largest_int_smaller_than_num > num
        ints.pop
        next
      end
      num -= largest_int_smaller_than_num
      roman_numerals += ROMAN_TRANSLATION[largest_int_smaller_than_num]
    end

    roman_numerals
  end
end
