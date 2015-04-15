class Squares
  attr_reader :square

  def initialize(square)
    @square = square
  end

  def square_of_sums
    (1..square).reduce(:+) ** 2
  end

  def sum_of_squares
    (1..square).map {|s| s ** 2 }.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
