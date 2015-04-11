class Fixnum
  def to_roman
    number_to_convert = self
    roman_numeral = ""
    roman_numerals = {M:1000, CM:900, D:500, CD:400, C:100, XC:90, L:50, XL:40, X:10, IX:9, V:5, IV:4, I:1 }
    roman_numerals.each do |key, value|
      number_of_numerals = number_to_convert / value
      number_to_convert -= value * number_of_numerals
      number_of_numerals.times do
        roman_numeral += key.to_s
      end
    end
    roman_numeral
  end
end
