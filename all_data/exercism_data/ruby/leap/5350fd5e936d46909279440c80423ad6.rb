class Year

  def initialize(year)
    @year = year
  end

  def leap?
    remainder?(4)  && 
      !remainder?(100) || 
        remainder?(400)
  end

  def remainder?(value)
    @year % value == 0
  end

end
