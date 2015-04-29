class Squares
  attr_reader :num, :square_of_sums, :sum_of_squares

  def initialize(num)
    @num = num
    @square_of_sums = (1..num).inject(:+)**2
    @sum_of_squares = (1..num).map { |x| x**2 }.inject(:+)
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end
end
