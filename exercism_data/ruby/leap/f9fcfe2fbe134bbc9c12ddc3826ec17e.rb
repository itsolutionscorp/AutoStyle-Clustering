class Year

  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    divisible_by_4? && (!century? || divisible_by_400?)
  end

  private

  def divisible_by_4?
    year % 4 == 0
  end

  def century?
    year % 100 == 0
  end

  def divisible_by_400?
    year % 400 == 0
  end

end
