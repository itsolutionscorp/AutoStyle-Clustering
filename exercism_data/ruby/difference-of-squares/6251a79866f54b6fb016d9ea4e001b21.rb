class Squares
  def initialize(num)
  	@list  = (1..num)
  end

  def square_of_sums
  	@list.inject(:+)**2
  end

  def sum_of_squares
  	@list.zip(@list).map {|i, j| i*j}.inject(:+)
  end

  def difference
  	square_of_sums - sum_of_squares
  end
end
