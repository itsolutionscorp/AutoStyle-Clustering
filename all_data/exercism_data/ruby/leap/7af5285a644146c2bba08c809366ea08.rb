class Year
  attr_reader :year
  private :year

  def initialize(year)
    @year = year
  end

  def leap?
    year.divisible_by?(400) || year.divisible_by?(4) && !year.divisible_by?(100)
  end
end

class Fixnum
  def divisible_by?(value)
    self % value == 0
  end
end
