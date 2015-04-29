module Roman
  def to_roman
    roman_units = {
      1000 => "M",
      900 => "CM",
      500 => "D",
      400 => "CD",
      100 => "C",
      90 => "XC",
      50 => "L",
      40 => "XL",
      30 => "XXL",
      10 => "X",
      9 => "IX",
      5 => "V",
      4 => "IV",
      1 => "I"
    }
    
    remainder = self
    roman_numeral = String.new

    ## Iterate over a sorted array of the keys, 
    # adding approppriate numbers Roman Numeral strings, 
    # and tracking the remaining integer value
    roman_units.keys.sort.reverse.each do |integer_unit|
      (remainder / integer_unit).times { roman_numeral << roman_units[integer_unit] }
      remainder %= integer_unit
    end

    roman_numeral
  end
end

class Fixnum; include Roman; end
