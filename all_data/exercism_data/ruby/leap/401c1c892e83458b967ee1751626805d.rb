class Year
  attr_reader :number
  def initialize(number)
    @number = number
  end

  def leap?
    divisible_by?(4) && !divisible_by?(100) || divisible_by?(400)
  end

  private

  def divisible_by?(i)
    number % i == 0
  end
end
