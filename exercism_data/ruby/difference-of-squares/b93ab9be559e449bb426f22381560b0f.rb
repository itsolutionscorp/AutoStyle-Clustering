class Squares
  def initialize(n)
    @n = Array.new (n) {|e| e + 1 }
  end

  def sum_of_squares
    @n.inject(0) {|sum, n| sum += n*n}
  end

  def square_of_sums
    @n.inject(:+) * @n.inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
