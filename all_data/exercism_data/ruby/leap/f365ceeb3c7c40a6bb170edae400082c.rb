class Year
  attr_reader :year

  def self.leap?(year)
    year = new(year)
    year.divisible_by?(4) &&
      !year.divisible_by?(100) ||
      year.divisible_by?(400)
  end

  def initialize(year)
    @year = Integer(year)
  end

  def divisible_by?(denominator)
    year % denominator == 0
  end
end
