class Squares
  attr_reader :square_of_sums, :sum_of_squares, :difference

  def initialize(num)
    @square_of_sums = ((0..num).to_a.inject { |sum, n| sum + n })**2
    @sum_of_squares = (0..num).to_a.inject { |sum, n| sum + n**2 }
    puts square_of_sums
    @difference = self.square_of_sums - self.sum_of_squares
  end

end
