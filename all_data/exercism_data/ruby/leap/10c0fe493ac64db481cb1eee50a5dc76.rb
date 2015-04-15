class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    quadricentennial? || !centennial? && quadrennial?
  end

  private

  def quadrennial?
    year.remainder(4) == 0
  end

  def centennial?
    year.remainder(100) == 0
  end

  def quadricentennial?
    year.remainder(400) == 0
  end
end
