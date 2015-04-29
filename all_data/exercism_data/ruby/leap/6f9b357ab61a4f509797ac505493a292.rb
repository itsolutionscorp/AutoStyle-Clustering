class Year
  def initialize(number)
    @number = number
  end

  def leap?
    divisible_by?(4) && (divisible_by?(400) || !divisible_by?(100))
  end

  private

  def divisible_by?(divisor)
    (@number % divisor).zero?
  end
end
