class Year
  def initialize(year)
    @year = year
  end

  def leap?
    divisible?(400,@year) || divisible?(4,@year) && !divisible?(100,@year)
  end

  def divisible?(divisor, dividend)
    dividend % divisor == 0
  end
end
