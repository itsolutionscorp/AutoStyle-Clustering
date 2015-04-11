class Year
  @year = nil
  def initialize(year)
    @year = year
  end
  def leap?
    if (!@year.nil? && ((@year % 100 == 0 && @year % 400 == 0)||(@year % 100 != 0 && @year % 4 == 0)))
      return true
    else
      return false
    end
  end
end
