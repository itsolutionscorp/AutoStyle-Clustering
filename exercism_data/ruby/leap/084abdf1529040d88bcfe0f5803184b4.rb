class Year
  def initialize(year)
    @year = year
  end

  def leap?
    vanilla_leap? && !century? || exceptional_century?
  end

  private
  
  attr_reader :year

  def vanilla_leap?
    divisible_by?(4)
  end

  def century?
    divisible_by?(100)
  end

  def exceptional_century?
    divisible_by?(400)
  end

  def divisible_by? years
    @year % years == 0
  end
end
