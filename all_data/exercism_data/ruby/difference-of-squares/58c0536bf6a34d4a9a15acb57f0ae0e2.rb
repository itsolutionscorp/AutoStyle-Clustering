class Squares

  attr_reader :max
  def initialize(max)
    @max = max
  end

  def sum_of_squares
    @max * ( max*(2*max + 3) + 1) / 6
  end

  def square_of_sums
    #range = (0..@max)
    #range.inject(:+) ** 2
    (max * (max + 1) / 2) ** 2
  end

  def difference
    square_of_sums - sum_of_squares
  end

end

puts Squares.new(9999999999).difference
