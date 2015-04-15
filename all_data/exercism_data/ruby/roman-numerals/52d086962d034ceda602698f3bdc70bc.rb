class Integer

  def to_roman
    num = self
    roman = ""
    roman_mapping.keys.each do |divisor|
      quotient, modulus = num.divmod(divisor)
      roman << roman_mapping[divisor] * quotient
      num = modulus
    end
    roman
  end

  private

  def roman_mapping
    {
      1000 => "M",
      900 => "CM",
      500 => "D",
      400 => "CD",
      100 => "C",
      90 => "XC",
      50 => "L",
      40 => "XL",
      10 => "X",
      9 => "IX",
      5 => "V",
      4 => "IV",
      1 => "I"
    }
  end

end
