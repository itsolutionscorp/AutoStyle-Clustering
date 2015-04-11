class Year
  def initialize(year)
    @year = year
  end

  def leap?
    return leap_century? if century?
    divisible_by? 4
  end

  private

  def century?
    divisible_by? 100
  end

  def leap_century?
    divisible_by? 400
  end

  def divisible_by?(divider)
    @year % divider == 0
  end
end
