class Fixnum
  #hashes within an array to keep track of powers of ten
  ROMAN_NUMERAL_LOOKUP = [
  ["I", "V"],
  ["X", "L"],
  ["C", "D"],
  ["M"]
  ]
  def to_roman
    roman = ""
    digits = self.to_s.split(//).map {|d| d.to_i}
    digits.each.with_index do |d, index|
      #i gives you 10 ** i of the digit in question
      i = digits.length - index - 1
      
      #roman digits are really in base ten, not in base 5
      #since if they were in base 5 the next symbols would be at 25 and 125
      #However, there is an odd syntax at 5, so we need to take into account
      #numbers above 5 and below 5
      
      if d == 9
        roman = roman + ROMAN_NUMERAL_LOOKUP[i][0] + ROMAN_NUMERAL_LOOKUP[i + 1][0]
      else
        if (d. > 4)
          roman = roman + ROMAN_NUMERAL_LOOKUP[i][1]
          d -= 5
        end
        
        if d < 4
          d.times {roman = roman + ROMAN_NUMERAL_LOOKUP[i][0]}
        elsif d == 4
          roman = roman + ROMAN_NUMERAL_LOOKUP[i][0] + ROMAN_NUMERAL_LOOKUP[i][1]
        end
      end
    end
    roman
  end
end
