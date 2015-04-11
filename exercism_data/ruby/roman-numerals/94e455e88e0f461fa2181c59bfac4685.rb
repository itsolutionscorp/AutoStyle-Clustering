class Fixnum

  def to_roman
    result = 'M' * (self / 1000 )

    result += roman_convert( self / 100, 'C','D','M')
    result += roman_convert( self / 10 , 'X','L','C') 
    result += roman_convert( self, 'I', 'V', 'X')
  end

  def roman_convert(digit,ones_char,fives_char,tens_char)
    mod_10 = digit % 10
    if  mod_10 == 9
      return ones_char + tens_char
    elsif mod_10 == 4
      return ones_char + fives_char
    elsif mod_10 >= 5
      return fives_char + ones_char * (mod_10 % 5)
    else
      return ones_char * mod_10
    end
  end

end
