class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    (self.year % 4 == 0 && self.year % 100 != 0) || self.year % 400 == 0
  end
end
