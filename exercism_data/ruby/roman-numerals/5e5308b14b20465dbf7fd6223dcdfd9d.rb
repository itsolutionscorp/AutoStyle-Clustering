class Fixnum
  
def to_roman
    arabic_number = self
    roman_number  = ""
    
    roman_equivalents.keys.each do |key|
      quotient, modulus = arabic_number.divmod(key)
      roman_number << roman_equivalents[key] * quotient
      arabic_number = modulus
    end

    roman_number
  end

  private

  def roman_equivalents 
    { 1000 => "M",
      900  => "CM", 
      500  => "D", 
      400  => "CD", 
      100  => "C",
      90   => "XC",
      50   => "L",
      40   => "XL",
      10   => "X",
      9    => "IX",
      5    => "V",
      4    => "IV",
      1    => "I" }
  end

end
