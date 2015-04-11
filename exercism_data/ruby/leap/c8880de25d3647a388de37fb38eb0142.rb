class Year
  
  def initialize(number)
    @number = number
  end
  
  def leap?
    exceptional_century || ( divisible_by_4 && !any_century )
  end 
  
  private
  def divisible_by_4
    @number % 4 == 0
  end  
  
  def any_century
    @number % 100 == 0
  end   
  
  def exceptional_century
    @number % 400 == 0 
  end  
    
end  
