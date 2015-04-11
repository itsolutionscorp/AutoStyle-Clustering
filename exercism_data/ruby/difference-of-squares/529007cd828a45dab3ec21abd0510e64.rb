class Squares
  def initialize(number)
    @n= number
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def sum_of_squares
    @sum_of_squares ||= (2*@n**3 + 3*@n**2 + @n)/6
  end

  def square_of_sums
    @square_of_sums ||= (@n*(@n+1)/2)**2
  end
end
