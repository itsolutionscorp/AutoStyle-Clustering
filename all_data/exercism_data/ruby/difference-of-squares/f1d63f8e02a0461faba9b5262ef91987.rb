class Squares

  def initialize n
    @num = n
  end 


  def sum ar
    ar.inject { |sum,n| sum+n }
  end

  def sum_of_squares
    sum (1..@num).map{ |i| i**2 }
  end

  def square_of_sums
    sum(1..@num)**2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
