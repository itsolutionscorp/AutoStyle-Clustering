class Year
  def initialize(year)
    @year = year
  end

  def leap?
    divisible_by? 4 and (not divisible_by? 100 or divisible_by? 400)
  end

  private
  def divisible_by? divisor
    @year % divisor == 0
  end
end
