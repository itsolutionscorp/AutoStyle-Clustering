module NumericExtensions
  refine Numeric do
    def divisible_by?(some_number)
      self % some_number == 0
    end
  end
end

using NumericExtensions

class Year
  def initialize(year)
    @year = year
  end

  def leap?
    leap_year? || leap_century?
  end

  private

  attr_reader :year

  def leap_year?
    return false if century?

    year.divisible_by?(4)
  end

  def century?
    year.divisible_by?(100)
  end

  def leap_century?
    year.divisible_by?(400)
  end
end
