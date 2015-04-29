class Year
  def initialize(year)
    @year = year
  end

  def leap?
    true if divisible_by_4 and !century?
  end

  def divisible_by_4
    true if @year.modulo(4) == 0
  end

  def century?
    true if @year.modulo(100) == 0 and @year.modulo(400) != 0
  end
end
