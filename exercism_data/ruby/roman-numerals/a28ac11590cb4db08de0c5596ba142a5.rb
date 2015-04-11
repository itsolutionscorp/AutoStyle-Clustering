class Integer
  
  def to_roman
    raise "Roman Number Not Computable" if (self < 1 || self > 3999)
    roman_number = []
    self.to_s.split("").each_with_index do |c,i|
      digit = c.to_i
      place_value = self.to_s.length-i
      roman_value << roman(digit,place_value)  
    end  
    roman_number.join()
  end  
   
  def roman(digit, place_value)
    case digit
    when 1..3
      case place_value 
       when 4 then return 'M' * digit
       when 3 then return 'C' * digit
       when 2 then return 'X' * digit
       when 1 then return 'I' * digit
      end   
    when 4
      case place_value
       when 3 then return 'CD'
       when 2 then return 'XL'
       when 1 then return 'IV'
      end   
   when 5
     case place_value
      when 3 then return 'D'
      when 2 then return 'L'
      when 1 then return 'V'
     end    
    when 6..8
      case place_value
       when 3 then return 'D'+('C'*(digit-5))
       when 2 then return 'L'+('X'*(digit-5))
       when 1 then return 'V'+('I'*(digit-5))
      end   
    when 9
      case place_value
       when 3 then return 'CM'
       when 2 then return 'XC'
       when 1 then return 'IX'
      end   
    end
  end  
  
end  
