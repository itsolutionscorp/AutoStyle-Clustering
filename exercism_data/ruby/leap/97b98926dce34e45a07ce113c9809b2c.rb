class Year
  def initialize(year)
    @year = year
  end

  def leap?
    (0 == @year % 4) && ((0 != @year % 100) || (0 == @year % 400))
  end
end
