class Squares
  attr_reader :square_of_sums, :sum_of_squares, :difference

  def initialize(limit)
    @sum_of_squares = (1..limit).map { |i| i * i }.inject(&:+)
    @square_of_sums = (1..limit).inject(&:+) ** 2
    @difference = @square_of_sums - @sum_of_squares
  end
end
