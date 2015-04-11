class Year

  def initialize(year)
    @year = year
  end

  def leap?
    vanilla_leap_year? && !leap_year_exception?
  end

  private

  attr_reader :year

  def vanilla_leap_year?
    year % 4 == 0
  end

  def leap_year_exception?
    year % 100 == 0 && year % 400 != 0
  end

end
