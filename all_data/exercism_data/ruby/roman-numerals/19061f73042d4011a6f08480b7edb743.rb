class Integer
  def to_roman
    number_under_conversion = self
    numerals.each_with_object([]) do |(arabic, roman_digit), result|
      result << roman_digit * (number_under_conversion / arabic)
      number_under_conversion %= arabic
    end.join
  end

  def numerals
    {
      1000 => "M",
      900  => "CM",
      500  => "D",
      400  => "CD",
      100  => "C",
      90   => "XC",
      50   => "L",
      40   => "XL",
      10   => "X",
      9    => "IX",
      5    => "V",
      4    => "IV",
      1    => "I"
    }
  end

end
