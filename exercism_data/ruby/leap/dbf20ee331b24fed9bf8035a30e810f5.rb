class Year
  def initialize(year)
    @year = year
  end

  def leap?
    divBy? 4 and (not divBy? 100 or divBy? 400)
  end

  private
  def divBy? divisor
    @year % divisor == 0
  end
end
