class Year
  attr_reader :year
  def initialize(year)
    @year = year
  end

  def self.leap?(year)
    new(year).leap?
  end

  def leap?
    divisible_by_4 && !divisible_by_100 || divisible_by_400
  end

  private

  def divisible_by_4
    year % 4 == 0
  end

  def divisible_by_100
    year % 100 == 0
  end

  def divisible_by_400
    year % 400 == 0
  end
end
