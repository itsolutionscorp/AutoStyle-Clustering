class Year
  attr_reader :numeric_year

  def initialize(numeric_year)
    @numeric_year = numeric_year
  end

  def leap?
    if divisible_by_400?
      true
    elsif divisible_by_100?
      false
    elsif divisible_by_4?
      true
    else
      false
    end
  end

  private

  def divisible_by_400?
    numeric_year % 400 == 0
  end

  def divisible_by_100?
    numeric_year % 100 == 0
  end

  def divisible_by_4?
    numeric_year % 4 == 0
  end
end
