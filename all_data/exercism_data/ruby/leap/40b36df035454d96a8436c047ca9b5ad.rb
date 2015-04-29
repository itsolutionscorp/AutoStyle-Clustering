class Year
  def initialize year
    @year = year
  end

  def leap?
    leap_century? || !common_year?
  end

  def common_year?
    @year % 4 != 0 || @year % 100 == 0
  end

  def leap_century?
    @year % 400 == 0
  end
end
