class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    if evenly_divisble_by_4?
      if evenly_divisble_by_100?
        if evenly_divisble_by_400?
          return true
        end
        return false
      end
      return true
    end
    false
  end

  private

  def evenly_divisble_by_4?
    evenly_divisble?(year, 4)
  end

  def evenly_divisble_by_100?
    evenly_divisble?(year, 100)
  end

  def evenly_divisble_by_400?
    evenly_divisble?(year, 400)
  end

  def evenly_divisble?(dividend, divisor)
    dividend % divisor == 0 
  end
end
