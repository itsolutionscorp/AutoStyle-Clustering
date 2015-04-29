class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    evenly_divisible_by?(4) && !evenly_divisible_by_100_but_not_400?
  end

  def evenly_divisible_by?(number)
    (year % number).zero?
  end

  def evenly_divisible_by_100_but_not_400?
    evenly_divisible_by?(100) && !evenly_divisible_by?(400)
  end

  class << self
    def leap?(year)
      new(year).leap?
    end
  end
end
