class Year

  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    return year_divisible_by_400? if century?
    year_divisible_by_4?
  end

  def year_divisible_by_4?
    @year % 4 == 0
  end

  def century?
    @year % 100 == 0 
  end

  def year_divisible_by_400?
    @year % 400 == 0
  end

end
