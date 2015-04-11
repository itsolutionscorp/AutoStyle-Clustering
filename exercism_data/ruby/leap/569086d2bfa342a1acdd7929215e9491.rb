class Year
  def self.leap?(year)
    new(year).leap?
  end

  def initialize(year)
    @year = year
  end

  def leap?
    # divisible by 4 AND (divisible by 100 XNOR divisible by 400)
    divisible_by?(4) && (divisible_by?(100) == divisible_by?(400))
  end

  private

  def divisible_by?(num)
    @year % num == 0
  end
end
