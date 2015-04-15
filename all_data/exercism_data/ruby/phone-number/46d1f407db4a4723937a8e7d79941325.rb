class PhoneNumber

  def initialize(number)
	  @number = number
  end
  
  def valid?
    @number.split("").any? {|x| x =~ /[a-zA-Z]/}
  end
  
  def formatted
    return "0000000000" if valid?
    format = @number.scan(/\d+/).join 
    return format if format.size == 10    
    return format[1,10] if format.size == 11 && format[0] == "1"
    "0000000000"
  end
  
  def number
    formatted  
  end
  
  def area_code
    formatted[0,3]
  end
  
  def to_s
    "(#{area_code}) #{number[3,3]}-#{number[6,4]}"
  end

end
