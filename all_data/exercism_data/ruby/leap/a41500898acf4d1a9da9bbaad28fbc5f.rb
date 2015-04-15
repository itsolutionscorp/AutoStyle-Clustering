class Year
  def self.leap?(year)
    new(year).leap?
  end

  def initialize(year)
    @year = year
  end

  def leap?
    return true if evenly_divisible_by(400)
    return false if evenly_divisible_by(100)
    evenly_divisible_by(4)
  end

  private

  def evenly_divisible_by(number)
    @year % number == 0
  end
end
