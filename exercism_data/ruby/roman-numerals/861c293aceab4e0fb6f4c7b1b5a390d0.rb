class Fixnum

  ROMAN_DICT = {
      1 => {1 => 'I', 5 => 'V'},
      2 => {1 => 'X', 5 => 'L'},
      3 => {1 => 'C', 5 => 'D'},
      4 => {1 => 'M'}
  }

  def to_roman
    roman_number = ''
    digit_array = self.to_s.chars.map(&:to_i)

    digit_array.each_with_index { |digit, i|
      case digit
        when 1, 2, 3
          roman_number << ROMAN_DICT[digit_array.length-i][1] * digit
        when 4
          roman_number << ROMAN_DICT[digit_array.length-i][1] + ROMAN_DICT[digit_array.length-i][5]
        when 5, 6, 7, 8
          roman_number << ROMAN_DICT[digit_array.length-i][5] + ROMAN_DICT[digit_array.length-i][1] * (digit-5)
        when 9
          roman_number << ROMAN_DICT[digit_array.length-i][1] + ROMAN_DICT[digit_array.length-i+1][1]
        else
          next
      end
    }
    roman_number
  end

end
