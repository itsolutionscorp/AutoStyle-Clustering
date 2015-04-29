class Integer

  def to_roman

    thousands = self / 1000
    hundreds = (self / 100) % 10
    decimals = (self / 10) % 10
    units  = self % 10

    roman_thousands = convert(thousands, 'M', nil, nil)
    roman_hundreds = convert(hundreds, 'C', 'D', 'M')
    roman_decimals = convert(decimals, 'X', 'L', 'C')
    roman_units = convert(units, 'I', 'V', 'X')

    roman_thousands << roman_hundreds << roman_decimals << roman_units
  end


  def convert(number, low, medium, high)
    result = ''
    if number < 4
      number.times { result << low}
    elsif number == 4
      result << low << medium
    elsif number < 9
      n = number % 5
      result << medium
      n.times {result << low}
    elsif number == 9
      result << low << high
    end
    result
  end

end
