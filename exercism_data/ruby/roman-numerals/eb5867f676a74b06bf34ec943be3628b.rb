class Integer
  def to_roman
    output = ""

    to_s.chars.each_with_index do |digit, index|
      output << build_roman(digit.to_i, to_s.length - index - 1)
    end

    output
  end

  private
  # Returns the roman numeral for digit * 10 ** exp
  def build_roman(digit, exp)
    roman_digits = [
      { 1 => "I", 5 => "V" },
      { 1 => "X", 5 => "L" },
      { 1 => "C", 5 => "D" },
      { 1 => "M" }
    ]

    if digit == 0
      return ""
    elsif digit <= 3
      return roman_digits[exp][1] * digit
    elsif digit == 4
      return roman_digits[exp][1] + roman_digits[exp][5]
    elsif digit == 5
      return roman_digits[exp][5]
    elsif digit <= 8
      return roman_digits[exp][5] + roman_digits[exp][1] * (digit - 5)
    else
      return roman_digits[exp][1] + roman_digits[exp+1][1]
    end
  end
end
