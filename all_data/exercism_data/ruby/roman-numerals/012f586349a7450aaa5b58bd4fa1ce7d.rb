class Fixnum

  def to_roman
    conversions = {
      1000 => 'M',
      900 => 'CM',
      500 => 'D',
      400 => 'CD',
      100 => 'C',
      90 => 'XC',
      50 => 'L',
      40 => 'XL',
      10 => 'X',
      9 => 'IX',
      5 => 'V',
      4 => 'IV',
      1 => 'I'
    }

    value = self
    result = ''
    while value > 0
      arabic, roman = conversions.detect { |a, r| a <= value }
      value -= arabic
      result += roman
    end
    result
  end 
end
