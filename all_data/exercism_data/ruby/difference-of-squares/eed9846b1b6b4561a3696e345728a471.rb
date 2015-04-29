class Squares
  def initialize(v) @v = v end
  def square_of_sums
    (1..@v).reduce(0){|t,v| t + v } ** 2
  end
  def sum_of_squares
    (1..@v).reduce(0){|t,v| t + v ** 2 }
  end
  def difference
    square_of_sums - sum_of_squares 
  end
end
