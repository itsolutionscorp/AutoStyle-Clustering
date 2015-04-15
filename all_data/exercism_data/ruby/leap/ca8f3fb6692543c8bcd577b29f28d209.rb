class Year

  def initialize(year)
    @year = year
  end

  def leap?
    return true if fourth_century?
    return true if divisible_by_four? unless century?
  end

  private
  def divisible_by_four?
    @year % 4 == 0
  end

  def century?
    @year % 100 == 0
  end

  def fourth_century?
    @year % 400 == 0
  end
end
