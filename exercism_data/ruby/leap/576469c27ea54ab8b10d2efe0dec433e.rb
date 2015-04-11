class Year

  def initialize(year)
    @year = year
  end

  def leap?
    @year.leap_year?
  end

end

class Fixnum

  def leap_year?
    (self % 4 == 0) && ((self % 100 != 0) || (self % 400 == 0))
  end

end
