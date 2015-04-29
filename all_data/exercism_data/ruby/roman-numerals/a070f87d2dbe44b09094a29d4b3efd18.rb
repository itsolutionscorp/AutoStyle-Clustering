class Fixnum
  def to_roman
    units     = self.to_s[-1].to_i if self.to_s[-1]
    tens      = self.to_s[-2].to_i if self.to_s[-2]
    hundreds  = self.to_s[-3].to_i if self.to_s[-3]
    thousands = self.to_s[-4].to_i if self.to_s[-4]

    roman_units     = roman_units_for(units)
    roman_tens      = roman_tens_for(tens)
    roman_hundreds  = roman_hundreds_for(hundreds)
    roman_thousands = roman_thousands_for(thousands)

    return "#{roman_thousands}#{roman_hundreds}#{roman_tens}#{roman_units}"
  end

  private

  def roman_for(number, one, five, ten)
    return if number.nil?

    case
    when number < 4
      one * number
    when number == 4
      "#{one}#{five}"
    when number == 5
      five
    when number <= 8
      "#{five}#{one * (number - 5)}"
    when number == 9
      "#{one}#{ten}"
    end
  end

  def roman_units_for(number)
    roman_for(number, 'I', 'V', 'X')
  end

  def roman_tens_for(number)
    roman_for(number, 'X', 'L', 'C')
  end

  def roman_hundreds_for(number)
    roman_for(number, 'C', 'D', 'M')
  end

  def roman_thousands_for(number)
    roman_for(number, 'M', nil, nil)
  end
end
