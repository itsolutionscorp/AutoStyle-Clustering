class Squares
  def initialize(n)
     @n = n
   end

  def self.square_of_sums
     ((1..@n).reduce(:+) || 1)**2
  end

  def self.sum_of_squares
    (1..@n).map { |x| x**2} .inject(0, &:+)
  end

  def self.difference
    square_of_sums(n) - sum_of_squares(n)
  end

end
