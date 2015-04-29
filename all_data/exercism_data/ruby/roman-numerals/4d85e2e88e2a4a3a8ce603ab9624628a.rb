class Fixnum
  @result = ""

  @column = [["", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"], ["", "X", "XX", "XXX" "XL", "L", "LX", "LXX", "LXXX", "XC"]]

  def self.to_roman
    self.to_s.chars.invert.map.with_index {|x, i| @result << @column[x][i.to_i]}
  end
end
