class Year
  def initialize(year)
    @year = year
  end
  
  def divisible_by?(divisor)
    @year % divisor == 0
  end
  
  def leap?
    case
    when divisible_by?(400); true
    when divisible_by?(100); false
    when divisible_by?(4); true
    else; false
    end
  end
end
