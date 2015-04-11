class Year
  
  def initialize date
    @date = Integer(date)
  end
  
  def leap?
    divisible_by_400? || (divisible_by_4? && !divisible_by_100?)
  end
  
  private 
  
  def divisible_by_4?
    (@date % 4 == 0)
  end
  
  def divisible_by_100?
    (@date % 100 == 0)
  end
  
  def divisible_by_400?
    (@date % 400 == 0)
  end
  
end
