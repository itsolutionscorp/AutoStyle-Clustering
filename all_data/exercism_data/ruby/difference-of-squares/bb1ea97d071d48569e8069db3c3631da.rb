class Squares
  def initialize(n)
    @num = n
  end
  def square_of_sums
    ((1..@num).to_a.inject(0) { |sum, num| sum + num }) ** 2
  end
  def sum_of_squares
    (1..@num).to_a.inject(0) { |sum, num| sum + num ** 2 }
  end
  def difference
    self.square_of_sums - self.sum_of_squares
  end
end
