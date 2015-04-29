class Year

  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    exceptional_century? || vanilla_leap_year? && !century?
  end

  private

  def vanilla_leap_year?
    year % 4 == 0
  end

  def century?
    year % 100 == 0
  end

  def exceptional_century?
    year % 400 == 0
  end

end


class Fixnum

  def leap_year?
    y = Year.new(self)
    y.leap?
  end

end
