class Fixnum
  def to_roman
    num = self
    roman_numeral = ""
    romans = {
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

    romans.keys.each do |n|
      roman_numeral += romans[n] * (num / n)
      num = num % n
    end
    roman_numeral
  end
end
