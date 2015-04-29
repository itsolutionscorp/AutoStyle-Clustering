class Year
  
  def initialize(yr)
    @yr = yr
  end
  
  def leap?
    exceptional_century || ( divisible_by_4 && !any_century )
  end 
  
  private
  def divisible_by_4
    @yr % 4 == 0
  end  
  
  def any_century
    @yr % 100 == 0
  end   
  
  def exceptional_century
    @yr % 400 == 0 
  end  
    
end  
