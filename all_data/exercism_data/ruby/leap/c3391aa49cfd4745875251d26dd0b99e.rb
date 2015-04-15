class Year
  attr_reader :year

  def self.leap?(year)
    year = Year.new(year)
    year.leap?
  end

  def initialize(year)
    @year = year
  end

  def leap?
    evenly_divisible?(year, 4) &&
      !evenly_divisible?(year, 100) ||
        evenly_divisible?(year, 400)
  end

  def evenly_divisible?(number, divisor)
    modulo = number % divisor
    modulo == 0
  end


end
