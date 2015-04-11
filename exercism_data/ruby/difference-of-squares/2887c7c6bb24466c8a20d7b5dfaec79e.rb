class Squares
  def initialize(n)
    @n = n
  end

  def sum_of_squares
    ((@n)*(@n + 1)*(2*@n + 1))/6 # Sum of squares of first n natural numbers is n(n+1)(2n+1)/6
  end


  def square_of_sums
    sum = (@n*(@n+1))/2 # Sum of first n natural numbers is n(n+1)/2
    sum * sum
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
