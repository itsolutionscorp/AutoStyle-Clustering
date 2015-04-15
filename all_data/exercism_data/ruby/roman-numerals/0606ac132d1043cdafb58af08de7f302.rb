class Fixnum
  ROMAN_NUMERALS = {
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
          0 => "",  
    }

  def to_roman
    n = self
    numeral = ""
    ROMAN_NUMERALS.each do |val, num|
      if val > 0 
       numeral << num * (n / val)
       n = n % val 
      end
    end
    numeral
  end

end
