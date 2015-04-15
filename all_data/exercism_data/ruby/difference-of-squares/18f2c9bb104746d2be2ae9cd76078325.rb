class Squares
  def initialize(number)
    @number = number
  end

  def square_of_sums
    square_of_sum = (0..@number).reduce(:+) ** 2
  end
  
  def sum_of_squares
    sum_of_squares = (0..@number).map {|item| item**2}.reduce :+
  end
  
  def difference
    square_of_sums - sum_of_squares
  end
end
