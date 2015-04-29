class Year
  attr_accessor :year

  def initialize(year)
    @year = year
  end

  def leap?
    quadricentennial? || leap_year?
  end

  private

  def quadricentennial?
    @year % 400 == 0
  end

  def leap_year?
    @year % 4   == 0 && 
    @year % 100 != 0
  end
end
