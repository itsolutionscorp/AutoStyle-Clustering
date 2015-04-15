class Squares
  def initialize(count)
    @square_of_sum = (1..count).inject(0, :+)**2
    @sum_of_squares = (1..count).map { |n| n**2 }.inject(0, :+)
    @difference = @square_of_sum - @sum_of_squares
  end
  
  def square_of_sums
    @square_of_sum
  end
  
  def sum_of_squares
    @sum_of_squares
  end
  
  def difference
    @difference
  end
end
