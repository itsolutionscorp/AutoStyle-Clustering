class Integer

  ROMAN_HASH = {
  1000 => "M",
  900 => "CM",
  500 => "D",
  400 => "CD",
  100 => "C",
  90 => "XC",
  50 => "L",
  40 => "XL",
  10 => "X",
  9 => "IX",
  5 => "V",
  4 => "IV",
  3 => "III",
  2 => "II",
  1 => "I"
  }
  def to_roman
    num = self
    ROMAN_HASH.reduce("") do |result, (reg_num, roman_num)|
      main_num, num = num.divmod(reg_num)
      result << roman_num * main_num
    end
  end
end
