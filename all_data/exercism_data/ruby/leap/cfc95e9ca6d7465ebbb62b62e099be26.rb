class Year

  def initialize(year)
    @year = year
  end

  def leap?
    if divisible_by_4?
      if century?
        return false unless divisible_by_400?
      end
      return true
    end
  end

  def divisible_by_4?
    @year % 4 == 0
  end

  def century?
    @year % 100 == 0
  end

  def divisible_by_400?
    @year % 400 == 0
  end
end
