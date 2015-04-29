class Year

  def initialize(year)
    @year = year
  end

  def leap?
    if century?
      @year % 400 == 0
    else
      @year % 4 == 0
    end
  end

  private

  def century?
    @year % 100 == 0
  end

end
