class Year
  def initialize(year)
    @year = year
  end

  def leap?
    return true if (@year % 400).zero?
    return false if (@year % 100).zero?
    return true if (@year % 4).zero?
    return false
  end
end
