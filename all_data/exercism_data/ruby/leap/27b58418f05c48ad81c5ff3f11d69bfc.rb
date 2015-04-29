class Year
  def initialize(year)
    @year = year
  end

  def leap?
    if @year % 100 == 0
      @year % 400 == 0
    else
      @year % 4 == 0
    end
  end
end
