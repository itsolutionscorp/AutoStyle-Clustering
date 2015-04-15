class Year
  def initialize(year)
    @year = year
  end

  def leap?
    return false if century?

    vanilla_leap_year?
  end

  private

  attr_reader :year

  def vanilla_leap_year?
    year % 4 == 0
  end

  def century?
    return false if exceptional_century?

    year % 100 == 0
  end

  def exceptional_century?
    year % 400 == 0
  end
end
