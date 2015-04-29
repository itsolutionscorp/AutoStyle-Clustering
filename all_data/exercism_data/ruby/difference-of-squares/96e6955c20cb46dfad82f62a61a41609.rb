class Squares
  def self.new(n)
    @ns = (1..n)
    self
  end
  def self.square_of_sums
    @ns.reduce(:+)**2
  end
  def self.sum_of_squares
    @ns.reduce(0) { |sum, n| sum + n**2 }
  end
  def self.difference
    square_of_sums - sum_of_squares
  end
end
