class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    year_divisible_by?(400) ||
    year_divisible_by?(4) && !year_divisible_by?(100)
  end

  def year_divisible_by?(number)
    year % number == 0
  end
end
