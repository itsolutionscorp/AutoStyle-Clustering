class Year

  def initialize(year)
    @year = year
  end

  def leap?
    return true  if exceptional_century?
    return false if century?
    return true  if leap_year?
  end

  private

  def century?
    @year % 100 == 0
  end

  def exceptional_century?
    century? && @year % 400 == 0
  end

  def leap_year?
    @year % 4 == 0
  end

end
