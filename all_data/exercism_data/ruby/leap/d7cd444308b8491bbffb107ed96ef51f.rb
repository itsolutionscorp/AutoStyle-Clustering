class Year
  def initialize(year)
    @year = year
  end

  def leap?
    normal_leap_year? || exceptional_century?
  end

  private

  def normal_leap_year?
    @year % 4 == 0 && @year % 100 != 0
  end

  def exceptional_century?
    @year % 400 == 0
  end
end
