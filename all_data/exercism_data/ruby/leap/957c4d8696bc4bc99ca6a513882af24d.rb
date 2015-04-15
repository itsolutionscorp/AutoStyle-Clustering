class Year
  
  def initialize date
    @date = Integer(date)
  end
  
  def leap?
    every_400th_year? || (every_4th_year? && !every_100th_year?)
  end
  
  private 
  
  def every_4th_year?
    (@date % 4 == 0)
  end
  
  def every_100th_year?
    (@date % 100 == 0)
  end
  
  def every_400th_year?
    (@date % 400 == 0)
  end
  
end
