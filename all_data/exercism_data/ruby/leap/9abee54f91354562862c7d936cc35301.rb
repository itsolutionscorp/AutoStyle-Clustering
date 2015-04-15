class Year
  def initialize(number)
    @number = number
  end

  def leap?
    divisible_by?(400) || (!divisible_by?(100) && divisible_by?(4))
  end

  private

  attr_reader :number

  def divisible_by?(divisor)
    (number % divisor).zero?
  end
end
