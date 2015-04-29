class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    year.divisible_by?(4) && !year.divisible_by?(100) ||
      year.divisible_by?(400)
  end
end

class Fixnum
  def divisible_by?(n)
    self % n == 0
  end
end
