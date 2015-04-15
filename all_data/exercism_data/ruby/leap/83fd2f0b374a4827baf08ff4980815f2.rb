class Year

  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    regular_leap? && !century? || millenium?
  end

  def regular_leap?
    year % 4 == 0
  end

  def century?
    year % 100 == 0
  end

  def millenium?
    year % 1000 == 0
  end
end
