class Year
  def initialize(year = 0)
    @year = year
  end

  def leap?
    div_by_400? || (div_by_4? && not_div_by_100?)
  end

private
  def div_by_4?
    0 == @year.modulo(4)
  end

  def not_div_by_100?
    0 != @year.modulo(100)
  end

  def div_by_400?
    0 == @year.modulo(400)
  end
end
