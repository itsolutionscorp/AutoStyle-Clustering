class Year
  def initialize(year)
    @year = year
  end

  def leap?
    vanilla_leap? || exceptional_century?
  end

  private

  def evenly_divisible?(divisor)
    @year % divisor == 0
  end

  def vanilla_leap?
    evenly_divisible?(4) && !evenly_divisible?(100)
  end

  def exceptional_century?
    evenly_divisible?(400)
  end
end
