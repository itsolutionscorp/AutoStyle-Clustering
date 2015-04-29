class Year

  def initialize(year)
    @year = year
  end

  def leap?
    leap_century? or (leap_year? and !exception_century?)
  end

  private
  def leap_century?
    @year % 400 == 0
  end

  def leap_year?
    @year % 4 == 0
  end

  def exception_century?
    @year % 100 == 0
  end

end
