class Year

  def self.leap?(year)
    new(year).leap?
  end

  def leap?
    is_leap_year? && (!is_century? || is_fourth_century?)
  end

  attr_reader :year

  private

  def initialize(year)
    @year = year
  end

  def is_leap_year?
    year % 4 == 0
  end

  def is_century?
    year % 100 == 0
  end

  def is_fourth_century?
    year % 400 == 0
  end
end
