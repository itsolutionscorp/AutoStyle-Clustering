class Squares

  def initialize(num)
    @num = num
    @sum = 0
    @square = 0
  end

  def square_of_sums
  	@num.times{ |count| @square += (count + 1) }
  	@square **= 2
  end

  def sum_of_squares
  	@num.times{ |count| @sum += (count + 1) ** 2 }
    @sum
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
