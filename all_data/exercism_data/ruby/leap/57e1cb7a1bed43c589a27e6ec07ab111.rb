class Year
  def initialize(year)
    @year = year
  end

  def leap?
    return leap_century? if century?
    dividable_by? 4
  end

  private

  def century?
    dividable_by? 100
  end

  def leap_century?
    dividable_by? 400
  end

  def dividable_by?(divider)
    @year % divider == 0
  end
end
