class Year
  def initialize(year)
    @year = year
  end

  def leap?
    quadrennial? && (! centennial? || quadricentennial?)
  end

  private

  def quadrennial?
    @year % 4 == 0
  end

  def centennial?
    @year % 100 == 0
  end

  def quadricentennial?
    @year % 400 == 0
  end
end

class Fixnum
  def leap_year?
    Year.new(self).leap?
  end
end
