class Year
  def initialize(year)
    @year = year
  end

  def leap?
    (standard_leap? && !century?) || exeptional_year?
  end

  def standard_leap?
    @year % 4 == 0
  end

  def century?
    @year % 100 == 0
  end

  def exeptional_year?
    @year % 400 == 0
  end
end
