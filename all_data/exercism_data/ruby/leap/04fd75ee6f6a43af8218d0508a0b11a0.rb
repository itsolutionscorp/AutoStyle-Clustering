class Year

  def initialize(year)
    @year = year
  end

  def leap?
    return true if fourth_century?
    return true if divisible_by?(4) unless century?
  end

  private

  def divisible_by?(divisor)
    @year % divisor == 0
  end
  
  def century?
    divisible_by? 100
  end

  def fourth_century?
    divisible_by? 400
  end

end
