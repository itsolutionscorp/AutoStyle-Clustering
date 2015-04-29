class Year
  def initialize( year )
    @year = year
  end

  def leap?
    year_century? ? year_fourth_century? : leap_year?
  end

  private
  def leap_year?
    @year % 4 == 0
  end

  def year_century?
    @year % 100 == 0
  end

  def year_fourth_century?
    @year % 400 == 0
  end
end
