class PhoneNumber
  
  DEFAULT = "0000000000"
  
  attr_accessor :number
  
  def initialize(number)
    @number = extract_number(number)
    @number = DEFAULT unless number?(@number)
  end  
  
  def extract_number(number)
    @number = number.scan(/(\w+)/).join 
    @number = @number[1..10] if full_us_number?(@number)    
    @number
  end  
  
  def full_us_number?(a_number)
    a_number.length == 11 && a_number[0] == "1"
  end    
  
  def number?(number)
    number && number.length == 10 && number.scan(/\D+/).length == 0
  end  
  
  def area_code
    @number.slice(0,3)
  end 
  
  def to_s
    "(#{@number.slice(0,3)}) #{@number.slice(3,3)}-#{@number.slice(6,4)}"
  end   
  
end  
