class Fixnum # I don't like monkey patching

  def to_roman
    numerals = {
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

    result = ""
    target = self

    numerals.keys.reverse.each do |num|
      while target >= num
          target -= num
          result += numerals[num]
      end
    end
    result
  end

end
