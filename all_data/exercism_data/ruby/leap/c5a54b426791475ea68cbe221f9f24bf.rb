class Year

  def initialize year
    @year = year
  end

  def leap?
    divisable_by_400? || (divisable_by_4? && !divisable_by_100?)
  end

  private

  def divisable_by_4?
    @year % 4 == 0
  end

  def divisable_by_100?
    @year % 100 == 0
  end

  def divisable_by_400?
    @year % 400 == 0
  end

end
