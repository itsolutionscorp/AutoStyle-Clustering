class Year
  def initialize(year)
    @year = year
  end

  def leap?
    return false if @year % 100 == 0 && @year % 400 != 0
    @year % 4 == 0
  end
end
