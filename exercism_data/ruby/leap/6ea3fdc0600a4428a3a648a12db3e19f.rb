class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    leap_candidate? && simple_leap? || complex_leap?
  end

  def leap_candidate?
    year % 4 == 0
  end

  def simple_leap?
    leap_candidate? && year % 100 != 0
  end

  def complex_leap?
    leap_candidate? && year % 100 == 0 && year % 400 == 0
  end
end
