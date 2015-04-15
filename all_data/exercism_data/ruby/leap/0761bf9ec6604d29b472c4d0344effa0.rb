class Year
  attr_reader :number

  def initialize(number)
    @number = number
  end

  def leap?
    if divisible_by?(4)
      divisible_by?(100) ? divisible_by?(400) : true
    end
  end

  private

  def divisible_by?(i)
    number % i == 0
  end
end

class Fixnum
  def leap_year?
    Year.new(self).leap?
  end
end
