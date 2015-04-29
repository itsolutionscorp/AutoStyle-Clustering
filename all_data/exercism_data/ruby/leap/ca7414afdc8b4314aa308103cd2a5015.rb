class Year
  def initialize(year)
    @year = year
  end

  def leap?
    @year % 400 == 0 ? true : (@year % 4 == 0 && @year % 100 != 0) ? true : false
  end
end
