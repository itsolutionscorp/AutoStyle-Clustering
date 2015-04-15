class Fixnum
  def to_roman
    raise "Number too big [" + self + "]." unless self <= 3000
    
    result = ""
    
    thousands = self / 1000
    result += convert_thousands(thousands)
    
    hundreds = (self % 1000) / 100
    result += convert_hundreds(hundreds)
    
    tens = (self % 100) / 10
    result += convert_tens(tens)
    
    units = (self % 10)
    result += convert_units(units)
    
    result
  end
  
  def convert_thousands(thousands)
    convert(thousands, nil, nil, "M")
  end
  
  def convert_hundreds(hundreds)
    convert(hundreds, "M", "D", "C")
  end
  
  def convert_tens(tens)
   convert(tens, "C", "L", "X")
  end
  
  def convert_units(units)
    convert(units, "X", "V", "I")
  end
  
  def convert(count, ten, five, one)
    result = ""
    if count == 9
      return one + ten
    end
    if count == 4
      return one + five
    end
    if count >= 5
      result += five
      count -= 5
    end
    result += one * count
    result
  end
end
