class Year
  def initialize(year)
    @year = year
  end

  def leap?
    return false if !divisible_by(4)
    return false if divisible_by(100) && !divisible_by(400)
    return true
  end

  def divisible_by(num)
    @year % num == 0
  end
end
