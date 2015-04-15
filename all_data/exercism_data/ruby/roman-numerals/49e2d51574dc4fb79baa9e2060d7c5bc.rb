class Integer
  def to_roman
    result = ""
    remaining = self
    
    begin  
      largest_contained = roman_symbols.keys.select{ |n| n <= remaining }.max
      result += roman_symbols[largest_contained]
      remaining -= largest_contained
    end while remaining > 0
    result
  end
  
  private
  
  def roman_symbols
    { 
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
  end
end
