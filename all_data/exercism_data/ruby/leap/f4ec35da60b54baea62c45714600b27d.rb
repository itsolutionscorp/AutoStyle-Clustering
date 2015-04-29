class Year
  def initialize(year)
    @year = year
  end

  def leap?
    return true if divisible_by? 400
    return false if divisible_by? 100

    divisible_by? 4
  end

  protected

  def divisible_by?(base)
    @year % base == 0
  end
end
