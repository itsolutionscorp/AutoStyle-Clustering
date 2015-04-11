class Numeric

  def to_roman
    number = self
    result = ""
    roman_numeral = {
      "I" => 1,
      "IV" => 4,
      "V" => 5,
      "IX" => 9,
      "X" => 10,
      "XL" => 40,
      "L" => 50,
      "XC" => 90,
      "C" => 100,
      "CD" => 400,
      "D" => 500,
      "CM" => 900,
      "M" => 1000,
    }

    roman_numeral.keys.reverse.each do |key|
      if (number / roman_numeral[key] >= 1)
        number -= roman_numeral[key]
        result << key
        
        return result << number.to_roman unless number == 0
      end
    end

    result
  end
end
