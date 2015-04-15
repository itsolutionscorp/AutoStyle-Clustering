class Year
  
  def initialize(_year)
    @y = _year
  end
  
  def leap?
    @y % 400 == 0 || ( @y % 4 == 0 && @y % 100 > 0 )
  end  
    
end  
