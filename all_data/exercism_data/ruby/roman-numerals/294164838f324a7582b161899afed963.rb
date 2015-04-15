class Integer

  ROMAN_MAP = { 
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

  def to_roman( input = self, output = "")
    # return the output if the number is 0
    return output if input == 0

    # check for each key in the hash
    ROMAN_MAP.keys.each do | factor |
      # use div mod to get the number of times divided and remainder
      numerals, remainder = input.divmod(factor)
      # output the roman representation
      output << ROMAN_MAP[factor] * numerals
      # run recursively
      return to_roman(remainder, output) if numerals > 0
    end
  end

end
