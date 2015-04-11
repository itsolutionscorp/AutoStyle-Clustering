class Year
  def initialize(year)
    @year = year
  end
  
  def leap?
    leap_year? && !century? || exceptional_century?
  end
  
  def leap_year?
    divisible_by? 4
  end
  
  def century?
    divisible_by? 100
  end
  
  def exceptional_century?
    divisible_by? 400
  end
  
  def divisible_by? n
    @year % n == 0
  end
end
