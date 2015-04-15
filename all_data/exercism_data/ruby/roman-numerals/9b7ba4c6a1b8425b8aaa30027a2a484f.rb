class Fixnum

  def to_roman
    number = self
    string = ''
    conversions.each do |decimal, roman|
      if number >= decimal
        string += roman * (number/decimal)
        number -= (decimal * (number/decimal))
      end
    end
    string
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
