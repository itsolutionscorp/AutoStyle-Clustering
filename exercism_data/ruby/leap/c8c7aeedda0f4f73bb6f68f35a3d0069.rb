class Year
  attr_reader :year

  def self.leap?(year)
    new(year).leap?
  end

  def initialize(year)
    @year = year
  end

  def leap?
   follows_century_rules = !divisible_by?(100) || divisible_by?(400)
   divisible_by?(4) && follows_century_rules
  end

  def divisible_by?(num)
    year % num == 0
  end
end
