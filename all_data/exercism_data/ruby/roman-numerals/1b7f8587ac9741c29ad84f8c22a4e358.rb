# extends the built in thing for integers with a new function, to_roman!
# admittely had to google for this one because i got completely stumped, i didnt understand why the tests were <number>.to_roman
# learned that it extends a built in class thing for numbers, so i went throught he entire thing to work out how it works
class Fixnum
  # constant
  ROMAN_NUMBERS = {
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
        1 => "I",  
  }

  def to_roman
    n = self
    roman = ""
    
    # i went through this to find out what it does
    ROMAN_NUMBERS.each do |value, letter|
      # multiplying a letter by a number returns the letter repeated by the number so G*2 = GG, G*1 = G, G*0 = ""
      roman << letter*(n / value)
      # it then divides the number by the current roman value, so this whittles the number down each loop to the final number
      n = n % value
    end    
    return roman
  end
end
