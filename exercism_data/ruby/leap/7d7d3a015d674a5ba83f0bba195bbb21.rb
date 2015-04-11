class Year
  attr_reader :year

  def initialize(year)
    @year = Integer(year)
  end

  def leap?
    year % 400 == 0 || (year % 100 > 0 && year % 4 == 0)
  end
end
