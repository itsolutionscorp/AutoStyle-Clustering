class Squares
  def initialize(limit)
    @sequence = (1..limit)
  end

  def square_of_sums
    @square_of_sums ||= @sequence.inject(:+) ** 2
  end

  def sum_of_squares
    @sum_of_squares ||= @sequence.map { |i| i ** 2 }.inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
