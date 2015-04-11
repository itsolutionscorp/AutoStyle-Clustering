class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    if divisible_by?(4)
      divisible_by?(100) ? divisible_by?(400) : true
    end
  end

  private

  def divisible_by?(number)
    true if year % number == 0
  end
end

class Fixnum
  def leap_year?
    Year.new(self).leap?
  end
end
