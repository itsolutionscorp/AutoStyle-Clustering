class Year
  def initialize(year)
    @year = year
  end

  def leap?
    simple_leap_year? || complex_leap_year?
  end

  private

  def simple_leap_year?
    by_4? && !by_100?
  end

  def complex_leap_year?
    by_400?
  end

  def by_4?
    @year % 4 == 0
  end

  def by_100?
    @year % 100 == 0
  end

  def by_400?
    @year % 400 == 0
  end
end

#on every year that is evenly divisible by 4
#  except every year that is evenly divisible by 100
#    except every year that is evenly divisible by 400.
