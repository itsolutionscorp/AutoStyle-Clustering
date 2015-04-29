class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    evenly_divisible_by?(4) && !evenly_divisible_by_100_but_not_400?
  end

  def evenly_divisible_by?(number)
    year % number == 0
  end

  def evenly_divisible_by_100_but_not_400?
    evenly_divisible_by?(100) && !evenly_divisible_by?(400)
  end

  class << self
    def leap?(year)
      self.new(year).leap?
    end
  end
end
