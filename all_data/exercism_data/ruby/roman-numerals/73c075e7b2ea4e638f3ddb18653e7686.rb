class Fixnum
  def to_roman
    num = self
    roman_numeral = ""
    romans = {
      1 => "I",
      4 => "IV",
      5 => "V",
      9 => "IX",
      10 => "X",
      40 => "XL",
      50 => "L",
      90 => "XC",
      100 => "C",
      400 => "CD",
      500 => "D",
      900 => "CM",
      1000 => "M"
    }
    romans.keys.reverse.each do |n|
      roman_numeral += romans[n] * (num / n)
      num = num % n
    end
    roman_numeral
  end
end
