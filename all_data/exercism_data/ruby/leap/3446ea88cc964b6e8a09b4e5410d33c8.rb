class Year
  
  def initialize(number)
    @number = number
  end
  
  def leap?
    return true if divisible_by? 400 
    return false if divisible_by? 100
    divisible_by? 4 
  end 
  
  private
  def divisible_by?(a_number)
    @number % a_number == 0
  end  
  
    
end  
