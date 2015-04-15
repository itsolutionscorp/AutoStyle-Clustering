class Fixnum

  NUMS = [[1000, "M"],
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
          [1, "I"]]

  def to_roman
    arabic = self
    str = ""
    NUMS.each do |num, sub|
      while arabic >= num
        str << sub
        arabic -= num
      end
    end
    str
  end
end
