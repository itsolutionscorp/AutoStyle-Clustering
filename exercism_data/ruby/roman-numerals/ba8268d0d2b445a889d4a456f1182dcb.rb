class Integer

  def to_roman
    num = self

    pairs = { "M" => 1000,
      "CM" => 900,
      "D" => 500,
      "CD" => 400,
      "C" => 100,
      "XC" => 90,
      "L" => 50,
      "XL" => 40,
      "X" => 10,
      "IX" => 9,
      "V" => 5,
      "IV" => 4,
      "I" => 1 }

      pairs.reduce("") do | roman_num, (letter, val)|
        while num >= val
          num -= val
          roman_num << letter
        end
        roman_num
      end
  end
end
