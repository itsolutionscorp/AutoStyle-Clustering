class Year
  
  def initialize year
    @year = year
  end
  
  def leap?
    every_400th_year? || (every_4th_year? && !every_100th_year?)
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
