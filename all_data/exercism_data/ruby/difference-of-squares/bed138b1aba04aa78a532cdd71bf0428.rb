class Squares

  def initialize(n)
    @num = n 
  end

  def sum_of_squares 
    (1..@num).reduce { |m,n| m += n**2 }
  end

  def square_of_sums
    (1..@num).reduce(:+)**2
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
