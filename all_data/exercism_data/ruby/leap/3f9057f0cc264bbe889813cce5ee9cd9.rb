class Year

  def initialize(year)
    @year = year
  end

  def leap?
    if divisible_by?(4)
      divisible_by?(400) || !divisible_by?(100)
    end
  end

  private

  def divisible_by?(number)
    @year % number == 0
  end

end
