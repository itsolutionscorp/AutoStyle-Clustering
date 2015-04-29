class Year
  def initialize(year)
    @year = year
  end

  def divisible_by?(divisor)
    @year % divisor == 0
  end

  def leap?
    (divisible_by?(4) && !divisible_by?(100)) || \
      (divisible_by?(100) && divisible_by?(400))
  end
end
