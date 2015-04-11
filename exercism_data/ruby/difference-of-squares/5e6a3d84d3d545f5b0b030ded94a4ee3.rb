class Squares
  attr_accessor :num

  def initialize(num)
  	@num = num
  end

  def sum_of_squares
  	(1..@num).collect{|x| x**2}.inject(:+)
  end

  def square_of_sums
  	(1..@num).each.inject(:+)**2
  end

  def difference
  	square_of_sums - sum_of_squares
  end

end
