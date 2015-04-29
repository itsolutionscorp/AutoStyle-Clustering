# year: exercism leap year tester
class Year
  attr_accessor :year

  def initialize(year)
    @year = year
  end

  def leap?
    divisible_by?(4, year) && (!divisible_by?(100, year) ||
      divisible_by?(400, year))
  end

  private

  def divisible_by?(divisor, year)
    year % divisor == 0
  end
end
