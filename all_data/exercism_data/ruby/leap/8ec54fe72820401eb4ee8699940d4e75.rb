# year: exercism leap year tester

class Year
  attr_accessor :year

  def initialize(year)
    self.year = year
  end

  def leap?
    if divisible_by_400?(year)
      true
    elsif divisible_by_100?(year)
      false
    elsif ordinary_leap_year?(year)
      true
    else
      false
    end
  end

  def divisible_by_400?(year)
    if year % 400 == 0
      true
    end
  end

  def divisible_by_100?(year)
    if year % 100 == 0 && year % 4 == 0
      true
    end
  end

  def ordinary_leap_year?(year)
    if year % 4 == 0
      true
    end
  end
end
