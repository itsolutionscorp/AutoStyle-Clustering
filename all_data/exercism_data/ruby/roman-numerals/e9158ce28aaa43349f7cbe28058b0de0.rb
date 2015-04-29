class Fixnum

  ROMAN = {
    1 => {
      1 => 'I',
      5 => 'V'
    },
    2 => {
      1 => 'X',
      5 => 'L'
    },
    3 => {
      1 => 'C',
      5 => 'D'
    },
    4 => {
      1 => 'M'
    }
  }

  ROMAN_NUM = {
    5 => {
      1 => 'V',
      2 => 'L',
      3 => 'D'
    },
    1 => {
      1 => 'I',
      2 => 'X',
      3 => 'C',
      4 => 'M'
    }
  }

  def to_roman
    number = self
    final_roman = ''
    while(number.to_i > 0)
      num_string = number.to_s
      roman_set = ROMAN[num_string.length]
      digit = num_string.chars.first.to_i

      if digit == 4 || digit == 9
        final_roman += roman_set[1]
        digit += 1
      elsif digit >= 5
        final_roman += roman_set[5]
        digit -= 5
      elsif digit > 0
        final_roman += roman_set[1]
        digit -= 1
      end

      number = digit > 0 ? digit.to_s + num_string[1..num_string.length] : num_string[1..num_string.length]
    end
    final_roman
  end
end
