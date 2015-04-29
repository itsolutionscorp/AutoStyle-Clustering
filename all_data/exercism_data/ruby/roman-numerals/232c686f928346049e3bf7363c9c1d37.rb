class Fixnum
  def self.roman_numerals
    {
      "I" => 1,
      "II" => 2,
      "III" => 3,
      "IV" => 4,
      "V" => 5,
      "VI" => 6,
      "VII" => 7,
      "VIII" => 8,
      "IX" => 9,
      "X" => 10,
      "XL" => 40,
      "L" => 50,
      "XC" => 90,
      "C" => 100,
      "CD" => 400,
      "D" => 500,
      "CM" => 900,
      "M" => 1000
    }.sort_by { |_, value| value}.reverse
  end

  def to_roman
    int = self
    numerals = ""
    mapping ||= Fixnum.roman_numerals

    while int > 0
      mapping.each do |numeral, value|
        if value <= int
          numerals << numeral
          int -= value
          break
        end
      end
    end

    numerals
  end
end
