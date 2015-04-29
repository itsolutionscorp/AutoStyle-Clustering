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
    year_divisible_by?(4)
  end

  def century?
    year_divisible_by?(100)
  end

  def century_leap_year?
    year_divisible_by?(400)
  end

  def year_divisible_by?(num)
    @year % num == 0
  end
end
