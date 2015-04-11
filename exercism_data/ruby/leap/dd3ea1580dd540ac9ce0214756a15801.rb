class Year
  def initialize(year)
    @year = year
  end

  def leap?
    divisible_by?(4) && (!divisible_by?(100) || divisible_by?(400))
  end

  private

  def year
    @year
  end

  def divisible_by?(num)
    year % num == 0
  end
end
