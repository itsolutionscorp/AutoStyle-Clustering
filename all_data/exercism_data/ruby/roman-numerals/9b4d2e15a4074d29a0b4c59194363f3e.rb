class Fixnum
  ROMANS_TO_VALUES = {
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

  def to_roman
    current = self
    ROMANS_TO_VALUES.inject("") do |roman_numerals, (roman_unit, value)|
      quotient, remain = current.divmod(value)
      current = remain
      roman_numerals << roman_unit * quotient
    end
  end
end
