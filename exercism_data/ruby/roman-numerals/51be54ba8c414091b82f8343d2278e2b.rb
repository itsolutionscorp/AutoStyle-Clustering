class Integer
  def to_roman
    digits_with_exp.each_with_object('') do |(digit,exponent),result|
      result << Integer::digit_to_roman(digit,exponent)
    end
  end

  # def to_roman
  #   decimal = self
  #   power_of_ten = 1000
  #   result = ''
  #   while (decimal >= 1) do
  #     digit = decimal/power_of_ten
  #     result << Integer::digit_to_roman(digit, power_of_ten)
  #     decimal -= (digit * power_of_ten)
  #     power_of_ten /= 10
  #   end
  #   result
  # end

private

  def digits_with_exp
    digits = self.to_s
    digits.chars.zip((digits.length - 1).downto(0))
  end

  ROMAN = {
    1000 => 'M',
    500 => 'D',
    100 => 'C',
    50 => 'L',
    10 => 'X',
    5 => 'V',
    1 => 'I'  
  }

  def self.digit_to_roman(digit, exponent)
    power_of_ten = 10 ** exponent
    factor = digit.to_i
    case factor
    when 0
      ''
    when 1..3
      ROMAN[power_of_ten] * factor
    when 4
      ROMAN[power_of_ten] + ROMAN[power_of_ten * 5]
    when 5
      ROMAN[power_of_ten * 5]
    when 6..8
      ROMAN[power_of_ten * 5] + ( ROMAN[power_of_ten] * (factor - 5) )
    when 9
      ROMAN[power_of_ten] + ROMAN[power_of_ten * 10]
    else
      raise "Nonsense! Factor is #{factor}. Should be in (0..9)."
    end
  end
end
