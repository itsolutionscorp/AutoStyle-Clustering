class PhoneNumber
  
  def initialize(digits)
    number = digits.scan(/[0-9]/).join("")
    @@number = number
  end

  def to_s
    number() #cleans up number
    return "(#{@@number[0..2]}) #{@@number[3..5]}-#{@@number[6..9]}" #not the prettiest, but it works... 
  end

  def number
    case @@number.length
   
    when 9 #number too short
     @@number = "0" * 10 

    when 11 #number too long
      if @@number[0] == "1" #but US country code
        @@number[0]=''  
      else
        @@number = "0" * 10 #non-US country code
      end
    
    end 
    
    return @@number
  end

  def area_code
    @@number[0..2]
  end

end
