class Squares

  def initialize( max_number )
    @max_number = max_number
  end

  def square_of_sums
    sum = @max_number * (@max_number+1)/2
    sum**2
  end

  def sum_of_squares
    (1..@max_number).inject(0) { |acc, n| acc + n**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
