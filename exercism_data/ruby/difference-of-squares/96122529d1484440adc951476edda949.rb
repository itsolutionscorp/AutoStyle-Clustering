class Squares

  attr_reader :range
  def initialize(bound)
    @range = (0..bound)
  end

  def sum_of_squares
    @range.inject {|sum, e| sum + e**2}
  end

  def square_of_sums
    @range.inject(:+) ** 2
  end

  def difference
    square_of_sums - sum_of_squares
  end

end

puts Squares.new(100).difference
