class Year

  def initialize(year)
    @year = year
  end

  def leap?
    (@year % 4 == 0) && ((@year % 100 != 0) || (@year % 400 == 0))
  end

end

class Fixnum

  def leap_year?
    (self % 4 == 0) && ((self % 100 != 0) || (self % 400 == 0))
  end

end
