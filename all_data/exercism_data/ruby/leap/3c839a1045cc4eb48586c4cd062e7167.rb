class Year
  def initialize(year)
    @year = year
  end

  def leap?
    leap_year? && !regular_century?
  end

  private
  attr_reader :year

  def regular_century?
    year % 100 == 0 && year % 400 != 0
  end

  def leap_year?
    year % 4 == 0
  end
end
