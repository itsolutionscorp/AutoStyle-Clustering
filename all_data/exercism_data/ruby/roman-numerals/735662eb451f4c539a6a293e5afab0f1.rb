class Fixnum
  @@map_to_roman = {
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
    int = self
    str = ""
    @@map_to_roman.each do |num, roman_char|
      multiples = int/num
      str += roman_char*multiples
      int -= multiples*num
    end
    str
  end
end
