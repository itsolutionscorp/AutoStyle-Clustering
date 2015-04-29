class Integer
  def to_roman
    number = self
    roman_number = ''

    roman_map = {
      "M" => 1000,
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
      "I" => 1
    }

    roman_map.each do |roman, value|
      how_often, number = number.divmod(value)
      roman_number += roman * how_often

      break if number === 0
    end

    roman_number
  end
end
