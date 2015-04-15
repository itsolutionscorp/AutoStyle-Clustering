class Year

  def initialize(year)
    @year = year
  end

  def leap?
    divisible_by_4? &&
    (not_century? || century_divisible_by_400?)
  end

  private
  def divisible_by_4?
    @year % 4 == 0
  end

  private
  def not_century?
    @year % 100 != 0
  end

  def century_divisible_by_400?
    @year % 400 == 0
  end

end
