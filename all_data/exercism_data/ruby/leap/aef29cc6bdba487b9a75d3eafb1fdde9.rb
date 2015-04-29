class Year
  def initialize(year)
    @year = year
  end

  def leap?
    return true if year_divisible_by? 400
    return false if year_divisible_by? 100

    year_divisible_by? 4
  end

  protected

  def year_divisible_by?(base)
    @year % base == 0
  end
end
