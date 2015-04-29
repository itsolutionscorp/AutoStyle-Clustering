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

class Fixnum

  def to_roman
    num = self
    ARABIC_TO_ROMAN.each.with_object("") do |(arabic, roman), roman_numeral|
      (num / arabic).times do
        roman_numeral << roman
      end
      num %= arabic
    end
  end

end
