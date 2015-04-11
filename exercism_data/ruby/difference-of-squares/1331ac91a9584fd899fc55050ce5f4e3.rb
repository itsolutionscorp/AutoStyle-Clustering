class Squares
  attr_reader :sequence

  def initialize(n)
    @sequence = 1.upto(n)
  end

  def square_of_sums
    sequence.reduce(:+)**2
  end

  def sum_of_squares
    sequence.reduce(0) { |a, e| a + e**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
