class Year
  def initialize(year)
    @year = year
  end

  def leap?
    return exceptional_century? if century?
    @year % 4 == 0
  end

  private

  def century?
    @year % 100 == 0
  end

  def exceptional_century?
    @year % 400 == 0
  end
end
