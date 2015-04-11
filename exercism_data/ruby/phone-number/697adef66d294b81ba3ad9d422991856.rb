class PhoneNumber
  
  DEFAULT = "0000000000"
  
  attr_accessor :number
  
  def initialize(number)
    @number = number.gsub( /(\D+)/,'')
    @number = DEFAULT if bad? @number  
    @number = @number.slice(1,10) if is_prefixed_by_1? @number
  end  
  
  def area_code
    @number.slice(0,3)
  end 
  
  def to_s
    "(#{@number.slice(0,3)}) #{@number.slice(3,3)}-#{@number.slice(6,4)}"
  end   

  private
  def bad?(a_number)
    a_number.length < 10 || a_number.length > 11 || (a_number.length == 11 && a_number[0] != "1") 
  end
  
  def is_prefixed_by_1?(a_number)
    a_number.length == 11 && a_number[0] == "1" 
  end  

end  
