class Year

  def initialize(year)
    @year = year
  end

  def leap?
    divisible_by?(400) || !divisible_by?(100) && divisible_by?(4)
  end

  private

  def divisible_by?(d)
    year % d == 0
  end

  attr_reader :year
end
