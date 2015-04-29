class Year
  attr_reader :year

  def initialize year
    @year = year
  end

  def leap?
    divisible?(4) && (! divisible?(100) || divisible?(400))
  end

  private

  def divisible? n
    year % n == 0
  end
end
