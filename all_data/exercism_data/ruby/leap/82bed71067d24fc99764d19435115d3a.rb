# year: exercism leap year tester
class Year
  attr_accessor :year

  def initialize(year)
    self.year = year
  end

  def leap?
    ordinary_leap_year?(year) && (!ordinary_century?(year) ||
    exceptional_century?(year))
  end

  def exceptional_century?(year)
    year % 400 == 0
  end

  def ordinary_century?(year)
    year % 100 == 0
  end

  def ordinary_leap_year?(year)
    year % 4 == 0
  end
end
