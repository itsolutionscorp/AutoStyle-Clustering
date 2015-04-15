class Year
  def initialize(number)
    @number = number
  end

  def leap?
    return true  if divisible_by?(400)
    return false if divisible_by?(100)
    return true  if divisible_by?(4)
    return false
  end

  private

  def divisible_by?(divisor)
    (@number % divisor).zero?
  end
end
