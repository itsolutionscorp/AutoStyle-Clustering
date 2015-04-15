class Roman
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

  def to_roman number
    result = ''
    PAIRS.keys.each do |divisor|
      quotient, remainder = number.divmod(divisor)
      result << PAIRS[divisor] * quotient
      number = remainder
    end
    result
  end
end

# puts Roman.new.to_roman(2678)
