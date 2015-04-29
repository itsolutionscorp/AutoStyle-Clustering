class Fixnum

  def to_roman
    arabic_numeral = self
    roman_numeral = ""
    ARABIC_TO_ROMAN.each do |arabic, roman|
      while arabic_numeral >= arabic
        arabic_numeral -= arabic
        roman_numeral << roman
      end
    end
    roman_numeral
  end

  private
  ARABIC_TO_ROMAN = {
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
