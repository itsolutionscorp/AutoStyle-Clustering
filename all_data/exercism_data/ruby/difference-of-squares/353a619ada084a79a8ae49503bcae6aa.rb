class Squares
  
  def initialize(limit)
    @limit = limit
    @nums = (1..@limit)
  end

  def square_of_sums
    sum = @nums.reduce(&:+)
    sum ** 2
  end

  def sum_of_squares
    squares = @nums.map{ |x| x**2 }
    squares.reduce(&:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
