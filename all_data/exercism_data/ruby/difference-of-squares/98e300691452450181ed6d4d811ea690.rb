class Squares
  attr_accessor :sum_of_squares, :square_of_sums

  def initialize(number)
    @sum_of_squares = compute_sum_of_squares(number)
    @square_of_sums = compute_square_of_sums(number)
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def compute_sum_of_squares(number)
    (1..number).to_a.reduce(0) { |sum, num| sum + num**2 }
  end

  def compute_square_of_sums(number)
    ((1..number).to_a.reduce(:+)) ** 2
  end
end
