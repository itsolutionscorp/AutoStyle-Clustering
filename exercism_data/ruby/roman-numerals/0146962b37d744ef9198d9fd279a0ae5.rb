class Fixnum


  def to_roman
    raise "Must use positive number!" if self <= 0

    romans = { 1000 => "M",
               900 => "CM",
               600 => "DC",
               500 => "D",
               400 => "CD",
               100 => "C",
               90 => "XC",
               60 => "LX",
               50 => "L",
               40 => "XL",
               10 => "X",
               9 => "IX",
               6 => "VI",
               5 => "V",
               4 => "IV",
               1 => "I"
    }

    left = self
    roman_string = ""
    romans.map do |key, value|
      while left >= key
        roman_string << value
        left -= key
      end
    end
    roman_string
  end
  
end
