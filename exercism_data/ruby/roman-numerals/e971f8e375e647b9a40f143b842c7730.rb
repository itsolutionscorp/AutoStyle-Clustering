class Fixnum
  ROMAN = {
    1 => "I",
    4 => "IV",
    5 => "V",
    9 => "IX",
    10 => "X",
    40 => "XL",
    50 => "L",
    90 => "XC",
    100 => "C",
    400 => "CD",
    500 => "D",
    900 => "CM",
    1000 => "M"
  }

  def to_roman
    output = ""
    current_key = 1
    current_number = self

    while current_number > 0
      ROMAN.each do |key, value|
        if current_number >= key
          current_key = key
        end
      end
      output += ROMAN[current_key]
      current_number -= current_key
    end
    output
  end
end
