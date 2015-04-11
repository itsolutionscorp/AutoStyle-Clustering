class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    return true if evenly_divisible_by_400?
    return false if evenly_divisible_by_4? && evenly_divisible_by_100?
    return true if evenly_divisible_by_4?
    return false
  end

  private

  def evenly_divisible_by_400?
    year % 400 == 0
  end

  def evenly_divisible_by_4?
    year % 4 == 0 
  end

  def evenly_divisible_by_100?
    year % 100 == 0
  end
end
