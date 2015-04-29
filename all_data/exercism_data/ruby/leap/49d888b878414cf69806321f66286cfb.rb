class Year

  def initialize(year)
    @year = year
  end

  def leap?
    return true  if divisable_by(400)
    return false if divisable_by(100)
    divisable_by(4)
  end

  private

  def divisable_by(value)
    @year % value == 0
  end

end
