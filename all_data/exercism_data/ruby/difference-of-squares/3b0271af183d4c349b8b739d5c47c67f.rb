class Squares
  def initialize(integer)
    @integer = integer
    @square_sum = 0
    @sum_square = 0
  end

  def square_of_sums
    for i in 1..@integer
      @square_sum += i
    end
    @square_sum = @square_sum**2
  end

  def sum_of_squares
    for i in 1..@integer
      @sum_square += i**2
    end
    @sum_square
  end

  def difference
    sum_of_squares
    square_of_sums
    @square_sum - @sum_square
  end
end
