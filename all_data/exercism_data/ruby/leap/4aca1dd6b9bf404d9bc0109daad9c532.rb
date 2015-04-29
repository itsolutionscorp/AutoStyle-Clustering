class Year
  def initialize(year)
    @year = year
  end

  def leap?
    divisible_by? 400 or (divisible_by? 4 and not divisible_by? 100)
  end

  private

  def divisible_by?(num)
    @year % num == 0
  end
end
