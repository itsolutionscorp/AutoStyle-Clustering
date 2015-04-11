class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    evenly_divisible_year?(400) || (evenly_divisible_year?(4) && !evenly_divisible_year?(100))
  end

  private
  def evenly_divisible_year?(divisor)
    year % divisor == 0
  end
end
