class Integer

  ROMAN_NUMERALS = { 1000 => "M",
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
    1 => "I",  
  }

  def to_roman
    return unless numeral = ROMAN_NUMERALS.detect{|k,v|k <= self}
    numeral_value, numeral_character = numeral
    div, mod = self.divmod(numeral_value)
    "#{numeral_character * div}#{mod.to_roman}"
  end

end
