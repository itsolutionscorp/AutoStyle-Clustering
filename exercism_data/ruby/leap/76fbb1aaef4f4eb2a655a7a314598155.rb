class Year
  def self.leap?(year)
    new(year).leap?
  end

  attr_reader :year
  private :year

  def initialize(year)
    @year = year
  end

  def leap?
    evenly_divisible_by_4 and !evenly_divisible_by_100 or evenly_divisible_by_400
  end

  private

  def evenly_divisible_by_4
    (year % 4) == 0
  end

  def evenly_divisible_by_100
    (year % 100) == 0
  end

  def evenly_divisible_by_400
    (year % 400) == 0
  end
end
