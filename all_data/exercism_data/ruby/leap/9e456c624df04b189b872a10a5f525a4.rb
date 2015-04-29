class Year
  def initialize(year)
    @year = year
  end

  def leap?
    if @year % 400 == 0 || (@year % 4 == 0 && @year % 100 != 0)
      return true
    else
      return false
    end
  end
end
