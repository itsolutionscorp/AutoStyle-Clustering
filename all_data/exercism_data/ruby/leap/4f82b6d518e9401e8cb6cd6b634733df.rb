class Year

  def self.leap?(year)
    new(year).leap?
  end

  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    fourth? && !excluded?
  end

  def excluded?
    hundreth? && !four_hundreth?
  end

  def fourth?
    year % 4 == 0
  end

  def four_hundreth?
    year % 400 == 0
  end

  def hundreth?
    year % 100 == 0
  end

end
