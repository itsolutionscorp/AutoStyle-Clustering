module RomanNumeral
  def to_roman
    result = ''
    # set self to variable in order to change value inside enum
    number = self 
    roman_mapping.keys.each do |divisable_by|
      # 
      quotient, modulus = number.divmod(divisable_by)
      result << roman_mapping[divisable_by] * quotient
      number = modulus
    end
    result
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

class Integer 
  include RomanNumeral
end
