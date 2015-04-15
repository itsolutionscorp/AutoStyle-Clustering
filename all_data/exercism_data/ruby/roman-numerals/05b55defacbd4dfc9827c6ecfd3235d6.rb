class Fixnum

  def to_roman
    number = self
    conversions.each_with_object('').collect do |(decimal, roman), string|
      while number >= decimal
        string += roman
        number -= decimal
      end
      string
    end.join
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
