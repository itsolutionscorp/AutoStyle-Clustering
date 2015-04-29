class Year

  def initialize(year)
    @year = year
  end

  def leap?
    evenly_divisible_by?(400) ||
      evenly_divisible_by?(4) && !evenly_divisible_by?(100)
  end

  private

  def evenly_divisible_by?(num)
    @year % num == 0
  end

end
