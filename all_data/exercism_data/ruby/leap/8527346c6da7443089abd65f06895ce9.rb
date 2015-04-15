class Year
  def initialize(year)
    @year = year
  end

  def leap?
    (standard_leap_year? && !century?) ||
    century_leap_year?
  end

private
  def standard_leap_year?
    @year % 4 == 0
  end

  def century?
    @year % 100 == 0
  end

  def century_leap_year?
    @year % 400 == 0
  end
end
