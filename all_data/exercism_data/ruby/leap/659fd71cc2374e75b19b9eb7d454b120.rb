class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    if evenly_divisible_by_100?
      return evenly_divisible_by_400?
    else
      return evenly_divisible_by_4?
    end
  end

  private

  def evenly_divisible_by_4?
    year % 4 == 0
  end

  def evenly_divisible_by_100?
    year % 100 == 0
  end

  def evenly_divisible_by_400?
    year % 400 == 0
  end
end
