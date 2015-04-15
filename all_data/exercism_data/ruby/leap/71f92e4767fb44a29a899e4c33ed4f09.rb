class Year
  def initialize(year)
    @year = year
  end

  def leap?
    evenly_divisable_by?(4) &&
      (!evenly_divisable_by?(100) || evenly_divisable_by?(400))
  end

  private

  def year
    @year
  end

  def evenly_divisable_by?(num)
    year % num == 0
  end
end
