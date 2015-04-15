class Fixnum

  ROMAN_MAP = {
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
    result = ""
    ROMAN_MAP.reduce(self) do |remainder, (key,value)|
      result << ((remainder/key).times.collect{value}.join || "")
      remainder = remainder % key
    end
    result
  end
end
