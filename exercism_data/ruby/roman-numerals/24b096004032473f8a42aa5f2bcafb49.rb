module RomanNumerals
  NUMERAL_GROUPS = {
    "M" => 1000, "CM" => 900, "D" => 500, "CD" => 400, "C" => 100, "XC" => 90,
    "L" => 50, "XL" => 40, "X" => 10, "IX" => 9, "V" => 5, "IV" => 4, "I" => 1
  }

  def to_roman
    remaining = self
    NUMERAL_GROUPS.each_with_object("") { |(numeral_group, value), output|
      while remaining >= value
        remaining -= value
        output << numeral_group
      end
    }
  end
end

class Integer
  include RomanNumerals
end
