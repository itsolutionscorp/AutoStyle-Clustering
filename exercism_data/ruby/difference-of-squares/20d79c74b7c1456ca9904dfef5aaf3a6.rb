class Squares
  def initialize(i)
    @inclusive_range = (1..i)
  end

  def square_of_sums
    sum = @inclusive_range.inject{|sum,x| sum + x }
    @square_of_sums_return = sum * sum
  end

  def sum_of_squares
    square = @inclusive_range.inject([]) { |mem, y| mem << y**2 }
    @sum_of_squares_return = square.inject{|sum, x| sum + x }
  end

  def difference
    square_of_sums
    sum_of_squares
    @square_of_sums_return - @sum_of_squares_return
  end
end
