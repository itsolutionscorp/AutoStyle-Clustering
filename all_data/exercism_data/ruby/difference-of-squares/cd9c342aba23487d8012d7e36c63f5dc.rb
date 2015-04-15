class Squares

  attr_reader :sum_of_squares, :square_of_sums, :difference

  def initialize(n)
    @sum_of_squares = (1..n).reduce { |s, i| s += i**2 }
    @square_of_sums = (1..n).reduce(:+) ** 2
    @difference     = (@sum_of_squares - @square_of_sums).abs
  end

end
