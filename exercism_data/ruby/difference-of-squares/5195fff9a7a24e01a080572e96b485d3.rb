class Squares

  attr_reader :sum_of_squares, :square_of_sums, :difference

  def initialize(num)
    @sum_of_squares = (1..num).to_a.map!{ |i| i**2 }.reduce(:+)
    @square_of_sums = (1..num).reduce(:+)**2
    @difference = (@sum_of_squares - @square_of_sums).abs
  end
end
