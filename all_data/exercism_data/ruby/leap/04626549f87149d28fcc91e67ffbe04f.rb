class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    if evenly_divisble_by_4?(year)
      return true if evenly_divisble_by_100?(year) && evenly_divisble_by_400?(year)
      return false if evenly_divisble_by_100?(year) && !evenly_divisble_by_400?(year)
      return true if !evenly_divisble_by_100?(year)
    end
    false
  end

  private

  def evenly_divisble_by_4?(year)
    evenly_divisble?(year, 4)
  end

  def evenly_divisble_by_100?(year)
    evenly_divisble?(year, 100)
  end

  def evenly_divisble_by_400?(year)
    evenly_divisble?(year, 400)
  end

  def evenly_divisble?(dividend, divisor)
    dividend % divisor == 0
  end
end
