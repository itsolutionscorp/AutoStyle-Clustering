class Integer
  ROMAN = {
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
    input = self
    convert_to_roman input, ""
  end

  def convert_to_roman num, roman
    ROMAN.each { |key, value|
      if num >= key
        num -= key
        roman << value
        break
      end
    }

    num > 0 ? convert_to_roman(num, roman) : roman
  end
end
