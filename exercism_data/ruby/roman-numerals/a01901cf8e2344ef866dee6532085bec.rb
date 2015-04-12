class Fixnum

  def roman_pairs
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

  def to_roman
    result = ''
    num = self
    roman_pairs.keys.each do |divisor|
      quotient, modulus = num.divmod(divisor)
      result += roman_pairs[divisor] * quotient
      num = modulus
    end
    return result
  end

end