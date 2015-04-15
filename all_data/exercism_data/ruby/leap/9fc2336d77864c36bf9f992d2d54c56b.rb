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
    divisible_by? 4
  end

  def four_hundreth?
    divisible_by? 400
  end

  def hundreth?
    divisible_by? 100
  end

  def divisible_by?(num)
    year % num == 0
  end

end
