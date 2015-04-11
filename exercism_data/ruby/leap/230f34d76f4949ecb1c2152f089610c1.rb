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
    modulo? 4
  end

  def four_hundreth?
    modulo? 400
  end

  def hundreth?
    modulo? 100
  end

  def modulo?(num)
    year % num == 0
  end

end
