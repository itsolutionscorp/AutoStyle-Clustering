class Year
  def initialize(year)
    @year = year
  end

  def leap?
    (year_divisible_by?(4) && !year_divisible_by?(100)) || year_divisible_by?(400)
  end

  private
  def year_divisible_by?(num)
    @year % num == 0
  end
end
