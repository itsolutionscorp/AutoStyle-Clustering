class Year
  attr_reader :number

  def initialize number
    @number = number
  end

  def leap?
    divisible_by?(4) && (!divisible_by?(100) || (divisible_by?(100) && divisible_by?(400)))
  end

  def divisible_by? this_number
    number % this_number == 0
  end
end
