class Year

  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    year_divisible?(4) && ((year % 100 != 0) || (year_divisible?(400)))
  end

private
  def year_divisible?(mod)
    year % mod == 0
  end
end
