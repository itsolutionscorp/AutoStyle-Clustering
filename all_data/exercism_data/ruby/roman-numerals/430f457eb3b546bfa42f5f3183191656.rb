class Fixnum
  RomanNumerals =
    [
      ["", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"],
      ["", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"],
      ["", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"],
      ["", "M", "MM", "MMM"]
    ]

  def to_roman
    digits = self.to_s.chars
    position = digits.length

    digits.map do |d|
      RomanNumerals[position-=1][d.to_i]
    end.join
  end
end
