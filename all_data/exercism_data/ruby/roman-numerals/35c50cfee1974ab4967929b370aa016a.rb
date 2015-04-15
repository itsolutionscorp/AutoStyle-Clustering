class Fixnum
  def roman_numerals
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
  
  def to_roman 
    rn = Array.new(self) do |i|
      target = i + 1
      roman_numerals.keys.sort { |a, b| b <=> a }.inject("") do |roman, div|
        times, target = target.divmod(div)
        roman << roman_numerals[div] * times
      end
    end
    rn.last
  end
end
