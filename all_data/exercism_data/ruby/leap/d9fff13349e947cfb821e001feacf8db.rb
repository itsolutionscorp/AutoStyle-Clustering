class Year
  def initialize(year)
    @year = year
  end

  def leap?
    leap_year? and not_exceptional_century?
  end

  private
  def leap_year?
    @year % 4 == 0
  end

  def not_exceptional_century?
    ! (@year % 100 == 0 && @year % 400 != 0)
  end
end
