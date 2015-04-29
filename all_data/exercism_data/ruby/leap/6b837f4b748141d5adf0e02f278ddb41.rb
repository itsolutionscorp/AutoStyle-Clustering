class Year
  attr_reader :number

  def initialize(number)
    @number = number
  end

  def leap?
    case
    when divisible_by?(400)
      true
    when divisible_by?(100)
      false
    when divisible_by?(4)
      true
    else
      false
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
