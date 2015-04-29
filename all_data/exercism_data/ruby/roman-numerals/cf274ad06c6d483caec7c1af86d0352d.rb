class Fixnum
  def to_roman
    hundreds = ["", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"]
    tens = ["", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"]
    ones = ["", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"]
    th = self.divmod(1000)
    output = "M" * th[0]
    h = th[1].divmod(100)
    output += hundreds[h[0]]
    tns = h[1].divmod(10)
    output += tens[tns[0]]
    output += ones[tns[1]]
  end
end
