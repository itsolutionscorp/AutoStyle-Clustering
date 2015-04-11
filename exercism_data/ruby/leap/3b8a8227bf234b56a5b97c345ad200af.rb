class Year
  def initialize(number)
    @number = number
  end

  def leap?
    divisible?(400) || (!divisible?(100) && divisible?(4))
  end

  private

  attr_reader :number

  def divisible?(by)
    (number % by).zero?
  end
end
