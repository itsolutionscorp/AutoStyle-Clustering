class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    divisible_by?(4) && !divisible_by?(100) || divisible_by?(400)
  end

  def divisible_by?(number)
    (year % number).zero?
  end

  class << self
    def leap?(year)
      new(year).leap?
    end
  end
end
