class Year

  def initialize(input_year)
    @year = input_year
  end

  def leap?
    four_hundred_years? || (fourth_year? unless century?)
  end

  private

  attr_reader :year

  def fourth_year?
    0 == @year % 4
  end

  def century?
    0 == @year % 100
  end

  def four_hundred_years?
    0 == @year % 400
  end

end
