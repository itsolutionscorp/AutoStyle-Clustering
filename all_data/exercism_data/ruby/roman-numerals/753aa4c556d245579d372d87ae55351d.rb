class Fixnum

  def to_roman
    number = self
    roman_numeral = ""
    conversions.each do |decimal, roman|
      while number >= decimal
        roman_numeral += roman
        number -= decimal
      end
    end
    roman_numeral
  end

  def conversions
    [
      [1000, "M"],
      [900, "CM"],
      [500, "D"],
      [400, "CD"],
      [100, "C"],
      [90, "XC"],
      [50, "L"],
      [40, "XL"],
      [10, "X"],
      [9, "IX"],
      [5, "V"],
      [4, "IV"],
      [1, "I"]
    ]
  end

end
