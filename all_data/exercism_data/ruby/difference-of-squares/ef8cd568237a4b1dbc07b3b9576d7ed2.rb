# Difference of squares

class Squares

  attr_reader :square_of_sums
  attr_reader :sum_of_squares

  def initialize(max)
    raise "Not a natural number" unless max.abs.integer?
    @max = max
    @square_of_sums = 0
    @sum_of_squares = 0

    1.upto(@max) do |n|
      @square_of_sums += n
      @sum_of_squares += n**2
    end
    @square_of_sums **= 2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
