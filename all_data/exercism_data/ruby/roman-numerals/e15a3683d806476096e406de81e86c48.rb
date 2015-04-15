class RomanNumerals

  attr_reader :roman

  def conversions
    {
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
  end

  def initialize(num)
    @num=num
    @roman=''
  end

  def convert(num=@num)
    conversions.keys.each {|divisor|
    result,remainder=num.divmod(divisor)
    @roman << conversions[divisor]*result
    return convert(remainder) if result > 0
    }
    #puts roman
    roman
  end
end

class Integer
  def to_roman
    RomanNumerals.new(self).convert
  end
end
