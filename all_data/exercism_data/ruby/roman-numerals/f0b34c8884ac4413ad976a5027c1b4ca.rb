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
      else 
        roman_set.keys.each do |num|
          if digit >= num
            final_roman += roman_set[num]
            digit -= num
          end
        end
      end

      number = digit > 0 ? digit.to_s + num_string[1..num_string.length] : num_string[1..num_string.length]
    end
    final_roman
  end
end
