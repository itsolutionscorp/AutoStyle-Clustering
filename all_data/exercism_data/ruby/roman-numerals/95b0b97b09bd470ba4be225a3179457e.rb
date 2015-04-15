class Fixnum
  def to_roman
    num = self
    dictionary.inject("") do |string, tuple|
      roman, integer = *tuple
      ratio = num / integer
      num -= integer * ratio
      string + roman * ratio
    end
  end

  private

    def dictionary
      {
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
    end
end
