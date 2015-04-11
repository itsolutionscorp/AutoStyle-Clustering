class Integer
  def to_roman
    romans = {1000 => "M", 900 => "CM", 500 => "D", 400 => "CD", 100 => "C", 90 => "XC", 50 => "L", 40 => "XL", 10 => "X", 9 => "IX", 5 => "V", 4 => "IV", 1 => "I"}
    i = self.to_i
    result = ''
    romans.each {|x, y|
      while i >= x
        result << y
        i -= x
      end
    }
    result
  end
end
