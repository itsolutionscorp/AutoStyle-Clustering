class Year
  def initialize(year)
    @year = year
  end

  def leap?
    (@year % 4).zero? || (@year % 400).zero? unless (@year % 100).zero? && (@year % 400).nonzero?
  end
end
