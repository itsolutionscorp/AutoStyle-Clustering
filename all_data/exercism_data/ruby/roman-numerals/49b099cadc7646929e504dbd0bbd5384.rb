class Fixnum

  # ROMAN_NUMERALS = {
  #   1 => 'I'
  #   5 => 'V'
  #   10 => 'X'
  #   50 => 'L'
  #   100 => 'C'
  #   500 => 'D'
  #   1000 => 'M'
  # }


  def to_roman
    #Number components
    thousands = self / 1000
    hundreds = self % 1000 / 100
    tens = self % 100 / 10
    ones = self % 10

    roman_string = ""

    #1000's - since we don't care for anything over 3000ish, just print the M's
    roman_string += "M" * thousands

    #100's
    roman_string += roman_digit(hundreds, 'C', 'D', 'M')

    #10's
    roman_string += roman_digit(tens, 'X', 'L', 'C')

    #1's
    roman_string += roman_digit(ones, 'I', 'V', 'X')

    return roman_string
  end

  def roman_digit digit, counter_string, five_counter_string, ten_counter_string
    case digit
    when 1,2,3
      return counter_string * digit
    when 4
      return counter_string + five_counter_string
    when 5
      return five_counter_string
    when 6,7,8
      return five_counter_string + counter_string * (digit - 5)
    when 9
      return counter_string + ten_counter_string
    else
      return ""
    end
  end
end
