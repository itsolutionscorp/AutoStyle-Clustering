class Year
  def initialize(year)
    @year = year
  end

  def leap?
    return true if exceptional_century?
    return false if century?
    return true if vanilla_leap_year?
    return false
  end

  private

  def vanilla_leap_year?
    @year % 4 == 0
  end

  def century?
    @year % 100 == 0
  end

  def exceptional_century?
    (@year % 400 == 0) && century?
  end
end
