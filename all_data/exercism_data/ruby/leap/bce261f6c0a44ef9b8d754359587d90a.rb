class Year
  def initialize(year)
    @year = year
  end

  def leap?
    if vanilla_leap?
      century? ? exceptional_century? : true
    else
      false
    end
  end

  private

  def evenly_divisible?(divisor)
    @year % divisor == 0
  end

  def vanilla_leap?
    evenly_divisible?(4)
  end

  def exceptional_century?
    evenly_divisible?(400)
  end

  def century?
    evenly_divisible?(100)
  end
end
