class Year

  def self.leap? year
    self.new(year).leap?
  end
  
  def initialize year
    @year = year
  end
  
  def leap?
    fourth_century? || (standard_leap_year? && !century?)
  end
  
  private 
  
  def standard_leap_year?
    @year % 4 == 0
  end
  
  def century?
    @year % 100 == 0
  end
  
  def fourth_century?
    @year % 400 == 0
  end
  
end
