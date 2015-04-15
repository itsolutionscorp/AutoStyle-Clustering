class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    divisible_by(4, year) && !divisible_by(100, year) || divisible_by(400, year)
  end

  def divisible_by(n, year)
    year % n == 0
  end

  def self.leap?(year)
    new(year).leap?
  end
end
