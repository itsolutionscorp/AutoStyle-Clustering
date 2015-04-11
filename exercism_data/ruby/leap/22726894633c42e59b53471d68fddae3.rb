class Year

  def initialize(year)
    @year = year
  end

  def leap?
    return true  if (@year % 400 == 0)
    return false if (@year % 100 == 0)
    return true  if (@year % 4 == 0) 
    return false
  end

end
