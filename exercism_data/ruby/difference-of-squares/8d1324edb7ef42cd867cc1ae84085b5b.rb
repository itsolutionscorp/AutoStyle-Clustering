class Squares
  def initialize(n)
    @range = Range.new(1,n).to_a
  end

  def sum_of_squares
    @range.map {|x| x**2}.reduce(:+)
  end

  def square_of_sums
    @range.reduce(:+) ** 2
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end
end
