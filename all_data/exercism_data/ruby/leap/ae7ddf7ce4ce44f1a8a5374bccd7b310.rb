class Year
  attr_reader :year

  def initialize year
    @year = year
  end

  def leap?
    normal_leap_year? && !century_exception?
  end

  private

  def normal_leap_year?
    year % 4 == 0
  end

  def century?
    year % 100 == 0
  end

  def century_exception?
    century? && !exceptional_century?
  end

  def exceptional_century?
    year % 400 == 0
  end
end
