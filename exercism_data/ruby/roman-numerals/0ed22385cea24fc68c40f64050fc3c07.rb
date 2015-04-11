class Fixnum


  def to_roman
    from_arabic(self)
  end
    
  def from_arabic(n)
    results = ""
    numerals.map do |value, chars|
      while n >= value
        results << chars
        n -= value
      end
    end
    results
  end    

  private
  def numerals
    {
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
      1 => "I"
    }  
  end

end
