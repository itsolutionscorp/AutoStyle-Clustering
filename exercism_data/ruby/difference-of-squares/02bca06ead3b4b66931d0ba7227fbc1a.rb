class Squares

  def self.square_of_sums (n)
     ((1..n).reduce(:+) || 1)**2
  end

  def self.sum_of_squares (n)
    (1..n).map { |x| x**2} .inject(0, &:+)
  end

  def self.difference(n)
    self.square_of_sums(n) - self.sum_of_squares(n)
  end

end
