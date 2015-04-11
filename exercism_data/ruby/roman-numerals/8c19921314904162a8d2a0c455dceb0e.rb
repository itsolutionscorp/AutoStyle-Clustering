class Fixnum

  ROMAN = {
    1 => {
      5 => 'V',
      1 => 'I'
    },
    2 => {
      5 => 'L',
      1 => 'X'
    },
    3 => {
      5 => 'D',
      1 => 'C'
    },
    4 => {
      1 => 'M'
    }
  }

  def to_roman
    num_string = self.to_s
    num_string.chars.map.with_index do |digit, i|
      digit_to_roman(digit.to_i, num_string.length-i)
    end.compact.join
  end

  def digit_to_roman digit, magnitude
    roman_set = ROMAN[magnitude]
    case digit
    when 4 
      roman_set[1] + digit_to_roman(5, magnitude)
    when 9
      roman_set[1] + digit_to_roman(1, magnitude+1)
    else
      roman_set.reduce('') do |numerals, (num, roman_val)|
        quotient, digit = digit.divmod(num)
        numerals += roman_val * quotient
      end
    end
  end


end
