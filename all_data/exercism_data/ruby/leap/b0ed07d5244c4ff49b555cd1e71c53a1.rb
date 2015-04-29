class Year

  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    divisible_by?(4) && (!divisible_by?(100) || divisible_by?(400))
  end

  private

  def divisible_by?(i)
    year % i == 0
  end

end
