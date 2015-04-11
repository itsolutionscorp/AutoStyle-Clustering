class Year
  def initialize(year)
    @year = year
  end

  def leap?
    divisible_by?(100) ? divisible_by?(400) : divisible_by?(4)
  end

  private

  def divisible_by?(amount)
    @year % amount == 0
  end

end
