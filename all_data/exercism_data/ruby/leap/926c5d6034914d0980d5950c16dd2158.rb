class Year
  def initialize(year)
    @year = year
  end

  def leap?
    exceptional_century? || vanilla_leap_year? && not_century
  end

  def vanilla_leap_year?
    @year % 4 == 0
  end

  def exceptional_century?
    @year % 400 == 0
  end

  def not_century
    @year % 100 != 0
  end

end
