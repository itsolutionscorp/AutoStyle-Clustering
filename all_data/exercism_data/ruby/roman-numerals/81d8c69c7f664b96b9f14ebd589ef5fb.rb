# version 1

class Fixnum
  
  ROMAN_NUMBERS = {
    1000  =>  "M",
    900   =>  "CM",
    500   =>  "D",
    400   =>  "CD",
    100   =>  "C",
    90    =>  "XC",
    50    =>  "L",
    40    =>  "XL",
    10    =>  "X",
    9     =>  "IX",
    5     =>  "V",
    4     =>  "IV",
    1     =>  "I"
  }
  
  def to_roman
    x = self
    roman_numeral = ""
    
    while x > 0
      highest_key = ROMAN_NUMBERS.keys.keep_if { |element| element <= x }.max
      roman_numeral += ROMAN_NUMBERS[highest_key]
      x -= highest_key
    end
    
    return roman_numeral
  end

end
