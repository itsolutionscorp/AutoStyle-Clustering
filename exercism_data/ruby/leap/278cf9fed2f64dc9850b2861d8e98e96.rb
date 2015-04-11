class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    vanilla_leap_year? && !non_exceptional_century?
  end

private
  def vanilla_leap_year?
    divisible_by?(4)
  end

  def non_exceptional_century?
    century? && !exceptional_century?
  end

  def century?
    divisible_by?(100)
  end

  def exceptional_century?
    divisible_by?(400)
  end

  def divisible_by?(value)
    year % value == 0
  end
end
