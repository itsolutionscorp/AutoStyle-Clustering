class Year
  def initialize(year)
    @year = year
  end

  def divisible_by_4(integer)
    integer % 4 == 0
  end

  def divisible_by_100(integer)
    integer % 100 == 0
  end

  def divisible_by_400(integer)
    integer % 400 == 0
  end

  def leap?
    if !divisible_by_100(@year)
      divisible_by_4(@year)
    elsif divisible_by_400(@year)
      divisible_by_4(@year)
    end
  end
end
