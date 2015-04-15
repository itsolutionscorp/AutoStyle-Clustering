class Squares
  attr_reader :square_of_sums, :sum_of_squares, :difference

  def initialize(count)
    calculate_square_of_sums(count)
    calculate_sum_of_squares(count)
    calculate_difference(count)
  end

  private

  def calculate_square_of_sums(count)
    @square_of_sums = (1..count).inject(:+) ** 2
  end

  def calculate_sum_of_squares(count)
    @sum_of_squares = (1..count).map {|n| n**2 }.inject(:+)
  end

  def calculate_difference(count)
    @difference = @square_of_sums - @sum_of_squares
  end
end
