class Integer
  def to_roman
    remainder = self
    numerals.each_with_object("") do |(arabic, roman_digit), result|
      result << roman_digit * (remainder / arabic)
      remainder %= arabic
    end
  end

  private

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
