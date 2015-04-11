class Year
  def initialize(year)
    @year = year
  end

  def leap?
    if @year % 4 == 0
      return (@year % 400 == 0 or @year % 100 != 0) 
    else
      return false
    end
  end
end
