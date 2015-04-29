class Fixnum

  def roman
    {
      1000 => "M",  
      900  => "CM",  
      500  => "D",  
      400  => "CD",
      100  => "C",  
      90   => "XC",  
      50   => "L",  
      40   => "XL",  
      10   => "X",  
      9    => "IX",  
      5    => "V",  
      4    => "IV",  
      1    => "I"  
    }
  end

  def to_roman
    n = self
    result = ""
    roman.each do |num, letter|
      result << letter * (n / num)
      n = n % num
    end
    return result
  end
end
