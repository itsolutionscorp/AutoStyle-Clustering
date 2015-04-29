class Year
  attr_reader :year

  def initialize(year)
    @year = year.to_i
  end

  def leap?
    evenly_divisible_by?(4) && (!evenly_divisible_by?(100) || evenly_divisible_by?(400))
  end

  private

  def evenly_divisible_by?(num)
    year % num == 0
  end
end
