class Fixnum
  def to_roman
    RomanNumerals.new(self).to_roman
  end
end

class RomanNumerals
  def initialize(num)
    @num = num
    @roman = {
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
    @num_in_roman = ""
    build_roman
  end
  
  def to_roman
    @num_in_roman
  end
  
  private 
  
  def build_roman
    @roman.each do |key, value|
      if @num >= key
        @num_in_roman << @roman[key]
        @num = @num - key
        build_roman
      end
    end
  end
end
