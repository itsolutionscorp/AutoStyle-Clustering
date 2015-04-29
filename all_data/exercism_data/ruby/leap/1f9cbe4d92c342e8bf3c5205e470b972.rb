class Year
  def initialize(number)
    @number = number
  end

  def leap?
    return false unless divisible_by? 4
    return true unless divisible_by? 100
    divisible_by?(400) and divisible_by?(100)
  end

  private

  def divisible_by?(what)
    @number % what === 0    
  end
end
