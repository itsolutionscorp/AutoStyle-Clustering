class Fixnum

  def to_roman
    result = ""
    remainder = self
    digits.each do |dec, char|
      while remainder >= dec
        result << char
        remainder -= dec
      end
    end
    result
  end

  private

  def digits
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
