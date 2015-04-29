class Year
  def initialize(year)
    @year = year
  end

  def leap?
    divisible?(400) || (divisible?(4) && !divisible?(100))
  end

  private

  def divisible?(num)
    @year % num == 0
  end
end
