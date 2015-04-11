class Fixnum
  MAP_TO_ROMAN = {
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
    1 => "I"
  }

  def to_roman
    str = ""
    MAP_TO_ROMAN.inject(self) do |int, (num, roman_char)|
      multiples, remainder = int.divmod(num)
      str << roman_char*multiples
      remainder
    end
    str
  end
end
