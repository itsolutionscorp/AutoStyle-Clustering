class Year
  def initialize(year)
    @year = year
  end

  def leap?
    vanilla_leap? || exceptional_century? ? true : false
  end

  private

  def evenly_divisible?(divisor)
    @year % divisor == 0 ? true : false
  end

  def vanilla_leap?
    evenly_divisible?(4) && !evenly_divisible?(100) ? true : false
  end

  def exceptional_century?
    evenly_divisible?(400) ? true : false
  end
end
