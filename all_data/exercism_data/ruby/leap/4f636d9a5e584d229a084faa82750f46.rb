class Year
  
  def initialize year
    @year = year
  end
  
  def leap?
    every_400th? || (every_4th? && !every_100th?)
  end
  
  private 
  
  def every_4th?
    @year % 4 == 0
  end
  
  def every_100th?
    @year % 100 == 0
  end
  
  def every_400th?
    @year % 400 == 0
  end
  
end
