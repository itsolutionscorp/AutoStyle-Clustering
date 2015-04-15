class Year
  attr_accessor :year

  def initialize(year)
    @year = year
  end

  def leap?
    return false unless evenly_divisible_by_four?
    evenly_divisible_by_100? ? evenly_divisible_by_400? : true
  end

  def evenly_divisible_by_four?
    @year % 4 == 0
  end

  def evenly_divisible_by_100?
    @year % 100 == 0
  end

  def evenly_divisible_by_400?
    @year % 400 == 0
  end
end
