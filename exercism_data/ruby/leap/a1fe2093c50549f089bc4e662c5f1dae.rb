class Year
  def initialize(year)
    @year = year
  end

  def leap?
    if (@year % 100) == 0
      return (@year % 400) == 0
    else
      return (@year % 4) == 0
    end
  end  
end
