class Year
  def initialize(year)
    @year = year
  end

  def leap?
    return true if evenly_divisible(@year, 400)
    return false if evenly_divisible(@year, 100)
    return true if evenly_divisible(@year, 4)
    false
  end

  private

  def evenly_divisible(numerator, denominator)
    quotient = numerator / denominator
    is_divisible = false
    is_divisible = true if quotient * denominator == numerator

    is_divisible
  end
end
