class Year
  def initialize(year)
    @year = year
  end

  def leap?
    quadrennial? && (!century? || quadrennial_century?)
  end

  def century?
    @year % 100 == 0
  end

  def quadrennial?
    @year % 4 == 0
  end

  def quadrennial_century?
    @year % 400 == 0
  end
end
