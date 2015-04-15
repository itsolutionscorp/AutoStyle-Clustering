class Squares
  def initialize(max_integer)
    @max_integer = max_integer
  end

  def square_of_sums
    ((0..@max_integer).inject(0) { |sum, val| sum + val }) ** 2
  end

  def sum_of_squares
    (0..@max_integer).inject(0) { |sum, val| sum + val**2 }
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end
end
