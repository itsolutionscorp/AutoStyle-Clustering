class Year
  def initialize(number)
    @number = number
  end

  def leap?
    divisible_by?(4) unless leap_exception?
  end

  private

  def leap_exception?
    divisible_by?(100) and not divisible_by?(400)
  end

  def divisible_by?(divisor)
    (@number % divisor).zero?
  end
end
