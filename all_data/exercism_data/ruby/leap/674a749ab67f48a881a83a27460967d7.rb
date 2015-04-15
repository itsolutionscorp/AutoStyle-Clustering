class Year
  attr_reader :number

  class << self
    def leap?(number_of_years)
      new(number_of_years).leap?
    end
  end

  def initialize(number)
    @number = number
  end

  def leap?
    fourth? && (!century? || fourth_century?)
  end

  def fourth?
    divisible_by? 4
  end

  def century?
    divisible_by? 100
  end

  def fourth_century?
    divisible_by? 400
  end

protected

  def divisible_by?(divisor)
    number % divisor == 0
  end

end
