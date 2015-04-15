class Squares
  def initialize(num_of_naturals)
    @num_of_naturals = num_of_naturals
    @square_of_sums = (1..@num_of_naturals).inject{ |sum, x| sum += x } ** 2
    @sum_of_squares = (1..@num_of_naturals).map{ |x| x ** 2 }.inject{ |sum, x| sum += x }
  end

  def square_of_sums
    @square_of_sums
  end

  def sum_of_squares
    @sum_of_squares
  end

  def difference
    (@square_of_sums - @sum_of_squares).abs
  end
end
