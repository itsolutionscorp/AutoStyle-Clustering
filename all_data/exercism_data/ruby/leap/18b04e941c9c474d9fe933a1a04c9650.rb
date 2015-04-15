class Year
  def self.leap?(year)
    new(year).leap?
  end

  def initialize(year)
    @year = year
  end

  def leap?
    (divisible_by?(4) && !divisible_by?(100)) || divisible_by?(400)
  end

  private 
  def divisible_by?(n)
  	@year % n == 0
  end
end
