class Fixnum

  def to_roman
    boundaries = {
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

    number = self
    numeral = ""
    boundaries.each do |value, letter|
      numeral << letter*(number / value)
      number = number % value
    end
    return numeral
  end
end
