class Year
  def initialize(year)
    @year = year
  end

  def leap?
    ordinary_leap? || exceptional_century?
  end

  private

  def ordinary_leap?
    any_fourth_year? && !century?
  end

  def exceptional_century?
    year % 400 == 0
  end

  def any_fourth_year?
    year % 4 == 0
  end

  def century?
    year % 100 == 0
  end

  attr_reader :year
end
