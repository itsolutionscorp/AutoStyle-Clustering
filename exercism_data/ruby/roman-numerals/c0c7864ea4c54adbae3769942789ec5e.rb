class Fixnum
  def to_roman
    result = ''
    number = ceil

    thousands = number.div 1000
    return '' if thousands > 3

    result += to_roman_helper(thousands, '', '', 'M')
    number -= thousands * 1000

    hundreds = number.div 100
    result += to_roman_helper(hundreds, 'M', 'D', 'C')
    number -= hundreds * 100

    tens = number.div 10
    result += to_roman_helper(tens, 'C', 'L', 'X')
    number -= tens * 10

    ones = number.div 1
    result += to_roman_helper(ones, 'X', 'V', 'I')

    result
  end

  private

  def to_roman_helper(count, upper_char, mid_char, base_char)
    result = ''
    if count == 9
      result += base_char + upper_char
    elsif count > 5
      result += mid_char
      (count - 5).times { result += base_char }
    elsif count == 5
      result += mid_char
    elsif count == 4
      result += base_char + mid_char
    else
      count.times { result += base_char }
    end
    result
  end
end
