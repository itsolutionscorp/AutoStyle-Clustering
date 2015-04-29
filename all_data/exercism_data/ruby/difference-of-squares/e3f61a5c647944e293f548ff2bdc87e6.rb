class Squares

  def initialize(num)
    @num = num
  end

  def square_of_sums
    (1..@num).to_a.inject { |sum, x| sum + x } ** 2
  end

  def sum_of_squares
    (1..@num).to_a.inject { |sum, x| sum + x**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
  
end
