class Year
  def initialize(n)
    @n = n
  end

  def leap?
    divisible_by?(4) &&
      (!divisible_by?(100) ||
       divisible_by?(400))
  end

  private

  attr_reader :n

  def divisible_by?(x)
    n % x == 0
  end
end
