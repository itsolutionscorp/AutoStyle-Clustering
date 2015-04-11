class Year
  
  def initialize(year)
    @year = year
  end
  
  def leap?
    divisible_by?(4) && (!divisible_by?(100) || divisible_by?(400))
  end
  
  def inspect
    @year
  end
  
  def to_s
    @year.to_s
  end
  
  protected
  
  def divisible_by?(denominator)
    @year % denominator == 0
  end
  
end
