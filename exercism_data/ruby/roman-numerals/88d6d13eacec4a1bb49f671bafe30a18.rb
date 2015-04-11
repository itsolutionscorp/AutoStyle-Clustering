# Extend Fixnum class
class Fixnum
  def to_roman
    ones = digit_to_roman(self % 10, 'I', 'V', 'X')
    tens = digit_to_roman(self / 10 % 10, 'X', 'L', 'C')
    hundreds = digit_to_roman(self / 100 % 10, 'C', 'D', 'M')
    thousands = digit_to_roman(self / 1000, 'M', '', '')
    thousands + hundreds + tens + ones
  end

  private

  def digit_to_roman(digit, one_char, five_char, ten_char)
    case digit
    when 0, 1, 2, 3
      return one_char * digit
    when 4, 5
      return one_char * (5 - digit) + five_char
    when 6, 7, 8
      return five_char + one_char * (digit - 5)
    when 9
      return one_char + ten_char
    end
  end
end
