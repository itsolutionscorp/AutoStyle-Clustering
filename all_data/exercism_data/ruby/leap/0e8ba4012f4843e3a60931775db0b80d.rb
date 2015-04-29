class Year

  def self.leap?(year)
    new(year).leap?
  end

  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    divisible_by?(400) || !divisible_by?(100) && divisible_by?(4)
  end

  private

  def divisible_by?(x)
    (year % x) == 0
  end

end
