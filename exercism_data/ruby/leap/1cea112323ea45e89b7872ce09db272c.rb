class Year
  def initialize(year_number)
    @year_number = year_number
  end

  def leap?
    divisible_by_four? and not exempt_century?
  end

  private

  attr_reader :year_number

  def divisible_by_four?
    divisible_by?(4)
  end

  def exempt_century?
    century? and not leap_century?
  end

  def century?
    divisible_by?(100)
  end

  def leap_century?
    divisible_by?(400)
  end

  def divisible_by?(divisor)
    year_number % divisor == 0
  end

end
