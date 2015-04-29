class Year

  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    return century_leap_year? if century?
    return normal_leap_year? if !century?
  end

  def normal_leap_year?
    @year % 4 == 0
  end

  def century?
    @year % 100 == 0 
  end

  def century_leap_year?
    @year % 400 == 0
  end

end
