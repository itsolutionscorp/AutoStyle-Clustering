class Year
  def initialize(year)
    @year = year
  end

  def leap?
    return leap_century? if century?
    divisionable_by? 4
  end

  private

  def century?
    divisionable_by? 100
  end

  def leap_century?
    divisionable_by? 400
  end

  def divisionable_by?(divider)
    @year % divider == 0
  end
end
