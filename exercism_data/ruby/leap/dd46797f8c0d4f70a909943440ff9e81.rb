class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    year_evenly_divisible_by?(100) ? year_evenly_divisible_by?(400) : year_evenly_divisible_by?(4)
  end

  private

  def year_evenly_divisible_by?(amount)
    year % amount == 0
  end

end
