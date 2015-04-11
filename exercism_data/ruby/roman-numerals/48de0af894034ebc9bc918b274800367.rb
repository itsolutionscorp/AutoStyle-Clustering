class Fixnum
  def to_roman
    numeral = ""
    numeral << roman_thousands_digit
    numeral << roman_hundreds_digit
    numeral << roman_tens_digit
    numeral << roman_ones_digit
  end

  private
  
  def roman_thousands_digit
    thousands_digit = self/1000 % 10
    if thousands_digit <= 3
      (1..thousands_digit).inject("") { |numeral, n| numeral += "M" }
    end
  end

  def roman_hundreds_digit
    hundreds_digit = self/100 % 10
    if hundreds_digit <= 3
      (1..hundreds_digit).inject("") { |numeral, n| numeral += "C" }
    elsif hundreds_digit == 4
      return "CD"
    elsif hundreds_digit <= 8
      (6..hundreds_digit).inject("D") { |numeral, n| numeral += "C" }
    elsif hundreds_digit == 9
      return "CM"
    end
  end

  def roman_tens_digit
    tens_digit = self/10 % 10
    if tens_digit <= 3
      (1..tens_digit).inject("") { |numeral, n| numeral += "X" }
    elsif tens_digit == 4
      return "XL"
    elsif tens_digit <= 8
      (6..tens_digit).inject("L") { |numeral, n| numeral += "X" }
    elsif tens_digit == 9
      return "XC"
    end
  end

  def roman_ones_digit 
    ones_digit = self % 10
    if ones_digit <= 3 
      (1..ones_digit).inject("") { |numeral, n| numeral += "I" }
    elsif ones_digit == 4
      return "IV"
    elsif ones_digit <= 8
      (6..ones_digit).inject("V") { |numeral, n| numeral += "I" }
    elsif ones_digit == 9
      return "IX"
    end
  end
end
