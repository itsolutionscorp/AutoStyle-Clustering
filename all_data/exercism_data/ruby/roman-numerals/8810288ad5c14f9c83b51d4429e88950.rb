class Integer

  ROMAN_CHARS = {
    1 => 'I',
    5 => 'V',
    10 => 'X',
    50 => 'L',
    100 => 'C',
    500 => 'D',
    1000 => 'M'
  }

  def get_roman_char(digit, power = 0)
    output = ''
    digit = digit.to_i
    coef = 10 ** power # coefficient
    case digit
    when 2..3
      digit.times { output += ROMAN_CHARS[coef] }
    when 4
      output = ROMAN_CHARS[coef] + ROMAN_CHARS[5 * coef]
    when 6..8
      output = ROMAN_CHARS[5 * coef]
      (digit - 5).times { output += ROMAN_CHARS[coef]}
    when 9
      output = ROMAN_CHARS[coef] + ROMAN_CHARS[10 * coef]
    else
      output = ROMAN_CHARS[digit * coef]
    end

    output
  end

  def to_roman
    pow = 0 # power
    output = []
    self.to_s.reverse.each_char { |digit|
      output.unshift(get_roman_char(digit, pow))
      pow += 1
    }

    output.join()
  end

end
