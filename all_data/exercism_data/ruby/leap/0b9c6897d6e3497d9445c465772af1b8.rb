class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    year % 4 == 0 && year % 100 != 0 || year % 400 == 0
  end
end
