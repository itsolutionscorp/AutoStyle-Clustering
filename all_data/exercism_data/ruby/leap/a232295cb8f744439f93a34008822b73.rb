class Year

  attr_accessor :year

  def self.leap?(year)
    new(year).leap?
  end

  def initialize(year)
    self.year = year
  end

  def leap?
    divisible_by?(4) unless divisible_by?(100) and !divisible_by?(400)
  end

  private

  def divisible_by?(number)
    year.modulo(number).zero?
  end

end
