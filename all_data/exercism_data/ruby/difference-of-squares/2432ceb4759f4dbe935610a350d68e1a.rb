class Squares
  attr_reader :sum_of_squares, :square_of_sums

  def initialize(max)
    @sum_of_squares = (1..max).map { |n| n ** 2 }.inject(:+)
    @square_of_sums = (1..max).inject(:+) ** 2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
