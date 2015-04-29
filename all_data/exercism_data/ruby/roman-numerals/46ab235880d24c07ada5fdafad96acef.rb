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
    s = ""
    x = self

    if x < 1 || x > 3000
      "Romans don't count that high, dude."
    end
    (0..12).each {|n| 
      while x >= RomanConversion.keys[n]
          s += RomanConversion.values[n]
          x -= RomanConversion.keys[n]
        end
    }

    s
  end

end
