class Fixnum
  def to_roman
    number = self
    pairings = { "M" => 1000,
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

    pairings.inject("") do |roman_numeral, (letters, value)|
      while number >= value
        number -= value
        roman_numeral << letters
      end
      roman_numeral
    end
  end
end
