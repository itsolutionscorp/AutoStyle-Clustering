class Integer
  ROMAN_NUMERALS = [[1000,  "M"],
                    [ 900, "CM"],
                    [ 500,  "D"],
                    [ 400, "CD"],
                    [ 100,  "C"],
                    [  90, "XC"],
                    [  50,  "L"],
                    [  40, "XL"],
                    [  10,  "X"],
                    [   9, "IX"],
                    [   5,  "V"],
                    [   4, "IV"],
                    [   1,  "I"]]

  def to_roman
    val = self
    ROMAN_NUMERALS.each.reduce("") do |acc, rn|
      value, str = rn[0], rn[1]
      while val >= value
        val -= value
        acc << str
      end
    end.join("")
  end

end
