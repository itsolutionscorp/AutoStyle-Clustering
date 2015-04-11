class Year
  def initialize(year)
    @year =  year
  end
  
  def leap?
    (evenly_divisible?(@year, 4) && 
      !(evenly_divisible?(@year, 100)) ||  
      evenly_divisible?(@year, 400) )
  end
  

  private
  def evenly_divisible?(dividend, divisor)
    dividend%divisor == 0
  end
  
end
