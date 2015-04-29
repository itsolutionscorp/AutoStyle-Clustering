class Year

  def initialize(year)
    @year = year
  end

  def leap?
    evenly_divisible_by_4? and (evenly_divisible_by_400? or not_evenly_divisible_by_100?)
  end

  def evenly_divisible_by_4?
    @year % 4 == 0
  end

  def not_evenly_divisible_by_100?
    not @year % 100 == 0
  end

  def evenly_divisible_by_400?
    @year % 400 == 0
  end

end
