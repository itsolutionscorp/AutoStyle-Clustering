class Integer
  def to_roman
    RomanHelper.to_roman(self)
  end
end

module RomanHelper
  def self.to_roman(n)
    ones, tens, hundreds, thousands = n.to_s.reverse.chars.map(&:to_i)
    thousands_to_roman(thousands) +
    hundreds_to_roman(hundreds) +
    tens_to_roman(tens) +
    ones_to_roman(ones)
  end

  def self.ones_to_roman(digit)
    digit_to_roman(digit, 'I', 'V', 'X')
  end

  def self.tens_to_roman(digit)
    digit_to_roman(digit, 'X', 'L', 'C')
  end

  def self.hundreds_to_roman(digit)
    digit_to_roman(digit, 'C', 'D', 'M')
  end

  def self.thousands_to_roman(digit)
    digit_to_roman(digit, 'M', '', '')
  end

  def self.digit_to_roman(digit, one_char, five_char, ten_char)
    case digit
    when (1..3) then one_char * digit
    when 4      then one_char + five_char
    when 5      then five_char
    when (6..8) then five_char + digit_to_roman(digit - 5, one_char, five_char, ten_char)
    when 9      then one_char + ten_char
    else ''
    end
  end
end
