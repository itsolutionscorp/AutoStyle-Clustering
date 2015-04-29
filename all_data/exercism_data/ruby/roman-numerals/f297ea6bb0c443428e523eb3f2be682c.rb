class Integer
  def to_roman
    roman_thousands_place + 
    roman_hundreds_place + 
    roman_tens_place + 
    roman_ones_place
  end

  private
  def roman_thousands_place
    digit = get_digit(1000)
    get_roman_numeral(digit, "M", "", "")
  end

  def roman_hundreds_place
    digit = get_digit(100)
    get_roman_numeral(digit, "C", "D", "M")
  end

  def roman_tens_place
    digit = get_digit(10)
    get_roman_numeral(digit, "X", "L", "C")
  end

  def roman_ones_place
    digit = get_digit(1)
    get_roman_numeral(digit, "I", "V", "X")
  end

  def get_digit(place)
    (self / place.to_i) % 10
  end

  def get_roman_numeral(digit, one_char, five_char, ten_char)
    case digit
    when 1 then one_char.to_s
    when 2 then one_char.to_s + one_char.to_s
    when 3 then one_char.to_s + one_char.to_s + one_char.to_s
    when 4 then one_char.to_s + five_char.to_s
    when 5 then five_char.to_s
    when 6 then five_char.to_s + one_char.to_s
    when 7 then five_char.to_s + one_char.to_s + one_char.to_s
    when 8 then five_char.to_s + one_char.to_s + one_char.to_s + one_char.to_s
    when 9 then one_char.to_s + ten_char.to_s
    else ""
    end
  end
end
