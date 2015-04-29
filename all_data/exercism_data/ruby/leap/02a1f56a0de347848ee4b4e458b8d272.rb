class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    is_divisible_by?(400) || (is_divisible_by?(4) && !is_divisible_by?(100))
  end

  def is_divisible_by?(divisor)
    year % divisor == 0
  end
end
