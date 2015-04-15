class Year
  attr_accessor :year
  def initialize year
    @year = year
  end
  
  def leap?
    divisible_by?(4) && (!divisible_by?(100) || divisible_by?(400))
  end
  
  private
  def divisible_by? x
    year.remainder(x) == 0
  end
end
