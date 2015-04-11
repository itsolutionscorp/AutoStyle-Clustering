class Year
  def initialize(year)
    self.year = year
  end

  def leap?
    divisible_by(4) && (!divisible_by(100) || divisible_by(400))
  end

  private

  attr_accessor :year

  def divisible_by(n)
    year % n == 0
  end
end
