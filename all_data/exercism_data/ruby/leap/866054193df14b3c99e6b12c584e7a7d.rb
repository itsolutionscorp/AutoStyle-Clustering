class Year
  attr_reader :year
  def initialize(year)
    @year = year
  end

  def leap?
    normal_leap_year? && leap_century?
  end

  private
  def normal_leap_year?
    year % 4 == 0
  end

  def leap_century?
    !normal_century? || exceptional_century?
  end

  def normal_century?
    year % 100 == 0
  end

  def exceptional_century?
    year % 400 == 0
  end
end
