class Squares
  attr_reader :square_of_sums, :sum_of_squares

  def initialize(n)
    numbers = 1..n

    @sum_of_squares = numbers.inject {|a,b| a += b ** 2 }
    @square_of_sums = numbers.inject {|a,b| a += b } ** 2
  end

  def difference
    @difference ||= @square_of_sums - @sum_of_squares
  end
end
