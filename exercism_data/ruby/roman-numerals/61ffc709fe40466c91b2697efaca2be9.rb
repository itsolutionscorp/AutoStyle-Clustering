class Integer
  attr_accessor :number
  PAIRS =
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
  def initialize number
    @number = number
  end

  def to_roman
    result = ''
    number = self
    PAIRS.keys.each do |divisor|
      quotient, remainder = number.divmod(divisor)
      result << PAIRS[divisor] * quotient
      number = remainder
    end
    result
  end
end

# exercputs 2500.to_roman
