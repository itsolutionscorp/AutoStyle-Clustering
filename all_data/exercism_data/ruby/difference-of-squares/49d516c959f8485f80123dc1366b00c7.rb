class Squares

  def initialize(num)
    @num = num 
  end

  def sum_of_squares
    (1..@num).map {|n| n**2}.inject(:+)
  end

  def square_of_sums
    (1..@num).inject(:+)**2 
  end
  
  def difference
  	square_of_sums - sum_of_squares
  end
end
