class Year
  def initialize(year)
    @year = year
  end

  def leap?
    divisible_by?(400) || divisible_by?(4) && !divisible_by?(100)
  end

  def self.leap?(year)
    Year.new(year).leap?
  end

  private

  def divisible_by?(divisor)
    @year % divisor == 0
  end
end
