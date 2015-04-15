class Squares
  
  def initialize(limit)
    @limit = limit
    @nums = (1..@limit)
  end

  def square_of_sums
    sum = @nums.reduce{ |x, y| x + y }
    sum ** 2
  end

  def sum_of_squares
    @nums.map{ |x| x**2 }.reduce{ |x,y| x + y }
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
