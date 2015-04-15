RomanConversion = {
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
    1 => "I",
  }

class Fixnum

  def to_roman
    roman_string = ""
    arabic_number = self

    if arabic_number < 1 || arabic_number > 3000
      "Romans don't count that high, dude."
    end
    (0..12).each {|n| 
      while arabic_number >= RomanConversion.keys[n]
          roman_string += RomanConversion.values[n]
          arabic_number -= RomanConversion.keys[n]
        end
    }

    roman_string
  end

end
