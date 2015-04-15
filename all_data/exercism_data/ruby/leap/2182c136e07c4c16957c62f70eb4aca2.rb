class Year
  def self.leap?(year)
    Year.new(year).leap?
  end

  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    divisible_by?(400) || divisible_by?(4) && !divisible_by?(100)
  end

  def divisible_by?(n)
    year.remainder(n).zero?
  end
end
