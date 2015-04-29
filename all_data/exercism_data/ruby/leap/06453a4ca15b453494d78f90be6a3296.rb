class Year

  def initialize(year)
    @year = year
  end

  def leap?
    century? ? divisible_by?(400) : divisible_by?(4)
  end

  private

  def divisible_by?(divisor)
    @year % divisor == 0
  end

  def century?
    divisible_by?(100)
  end

end
