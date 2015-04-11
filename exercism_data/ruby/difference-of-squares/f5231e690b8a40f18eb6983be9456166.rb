class Squares

  def initialize(n)
    @nums = (1..n)
  end

  def square_of_sums
    @nums.reduce(0,:+)**2
  end

  def sum_of_squares
    @nums.collect{ |num| num**2 }.reduce(0,:+)
  end

  def difference
     square_of_sums - sum_of_squares
  end

end
