class Squares
  def initialize(num)
  	@num = num
  end

  def num
  	@num
  end

  def num_array
  	(1..num).to_a
  end

  def sum
    @sum = num_array.inject(:+)
  end

  def squares
  	@squares = []
  	num_array.each { |n| @squares << n*n }
  	@squares
  end

  def square_of_sums
  	@square_of_sums = sum*sum
  end

  def sum_of_squares
  	@sum_of_squares = squares.inject(:+)
  end

  def difference
  	square_of_sums - sum_of_squares
  end
end
