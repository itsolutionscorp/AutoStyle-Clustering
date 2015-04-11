class Integer
  def to_roman
    value = self

    roman = ""

    numeral_mappings = [
      [1000, "M"], [900, "CM"], [500, "D"], [400, "CD"],
      [100, "C"],  [90, "XC"],  [50, "L"],  [40, "XL"],
      [10, "X"],   [9, "IX"],   [5, "V"],   [4, "IV"],  [1, "I"]
    ]

    numeral_mappings.each do |mapping|
      number, numeral = mapping

      roman << remainder_to_numerals(value, number, numeral)
      value = value % number
    end

    roman
  end

  private
  def remainder_to_numerals(value, divisor, char)
    out = ""
    (value / divisor).to_i.times { out << char }
    out
  end
end
