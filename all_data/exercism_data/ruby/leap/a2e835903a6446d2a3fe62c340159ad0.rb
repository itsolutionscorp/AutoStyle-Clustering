class Year

  def initialize(year)
    @year = year
  end

  def leap?
    if divisible_by(4) && !divisible_by(100) || divisible_by(400)
      true
    else
      false
    end
  end

  private

  def divisible_by(x)
    @year % x == 0
  end

end
