class Fixnum
  def to_roman
    arabic = self
    roman = ''
    roman_numerals.each { |division, roman_numeral|
      while arabic >= division do
        roman << roman_numeral
        arabic -= division
      end
    }
    roman
  end
  def roman_numerals
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
