class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def evenly_divisible_by?(number)
    year % number == 0
  end

  def evenly_divisible_by_4?
    evenly_divisible_by? 4
  end

  def evenly_divisible_by_100?
    evenly_divisible_by? 100
  end

  def evenly_divisible_by_400?
    evenly_divisible_by? 400
  end

  def evenly_divisible_by_100_unless_evenly_divisible_by_400?
    evenly_divisible_by_100? && !evenly_divisible_by_400?
  end

  class << self
    def leap?(year)
      year = Year.new(year)

      year.evenly_divisible_by_4? &&
        !year.evenly_divisible_by_100_unless_evenly_divisible_by_400?
    end
  end
end
