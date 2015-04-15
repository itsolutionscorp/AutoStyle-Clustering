class Numeric

  def to_roman
    str = "%04d" % self

    roman = []
    roman << convert_digit(str[0], 0)
    roman << convert_digit(str[1], 1)
    roman << convert_digit(str[2], 2)
    roman << convert_digit(str[3], 3)
    roman.join("")
  end

  SINGLE = ["M", "C", "X", "I"]
    # symbolisiert 1 bei den Tausendern, Hundertern, Zehnern, Einern
  FIVER = ["", "D", "L", "V"]
    # symbolisiert 5 bei den Hundertern, Zehnern, Einern

  def convert_digit(digit, position)
    digit = digit.to_i
    return if digit == 0
    roman = []

    digit.times { roman << SINGLE[position] } if digit <= 3
    roman << "#{SINGLE[position]}#{FIVER[position]}" if digit == 4
    roman << FIVER[position] if digit >= 5 && digit <=8
    (digit - 5).times { roman << SINGLE[position] } if digit >= 6 && digit <=8
    roman << "#{SINGLE[position]}#{SINGLE[position - 1 ]}" if digit == 9

    return roman.join("")
  end

end
