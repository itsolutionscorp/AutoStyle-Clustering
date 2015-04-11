# Difference of squares

class Squares

  attr_reader :square_of_sums
  attr_reader :sum_of_squares

  def initialize(max)
    @square_of_sums = 0
    @sum_of_squares = 0
    @max = max

    precompute
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private
  def precompute
    1.upto(@max) do |n|
      @square_of_sums += n
      @sum_of_squares += n**2
    end
    @square_of_sums **= 2
  end
end
