class Year
  def initialize(year)
    @year = year
  end

  def leap?
    (divisible_by_four? && !divisible_by_one_hundred?) ||
    divisible_by_four_hundred?
  end

private
  def divisible_by_four?
    @year % 4 == 0
  end

  def divisible_by_one_hundred?
    @year % 100 == 0
  end

  def divisible_by_four_hundred?
    @year % 400 == 0
  end
end
