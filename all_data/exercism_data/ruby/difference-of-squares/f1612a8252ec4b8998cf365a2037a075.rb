class Squares
  attr_reader :integer

  def initialize(integer)
    @integer = integer
  end

  def process(power, integer)
    total = 0
    until integer < 0
      total += integer**power
      integer -= 1
    end
    total
  end

  def sum_of_squares
    process(2, integer)
  end

  def square_of_sums
    process(1, integer)**2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
