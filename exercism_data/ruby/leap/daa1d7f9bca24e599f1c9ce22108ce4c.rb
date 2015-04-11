class Year

  def self.leap?(year)
    new(year).leap?
  end

  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    divisible_by?(4) && !excluded?
  end

  def excluded?
    divisible_by?(100) && !divisible_by?(400)
  end

  def divisible_by?(num)
    year % num == 0
  end

end
