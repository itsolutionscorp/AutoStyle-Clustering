module RomanNumeral
  NUMERALS = {
    1 => 'I',
    5 => 'V',
    10 => 'X',
    50 => 'L',
    100 => 'C',
    500 => 'D',
    1000 => 'M'
  }

  def to_roman
    if NUMERALS.has_key?(self)
      NUMERALS[self]
    else
      four_digit_num = '%04d' % self
      result = []
      four_digit_num.chars.each_with_index do |number, i|
        location = 10 ** (3 - i)
        result << replace_with_numeral(location, number.to_i)
      end
      result.join
    end
  end

  def replace_with_numeral(location, number)
    case number
      when 0, 1, 2, 3
        NUMERALS[location] * number
      when 4
        NUMERALS[location] + NUMERALS[location * 5]
      when 5, 6, 7, 8
        NUMERALS[location * 5] + (NUMERALS[location] * (number - 5))
      when 9
        NUMERALS[location] + NUMERALS[location * 10]
      else
    end
  end

end

class Integer
  include RomanNumeral
end
