class Fixnum
  def square
    self ** 2
  end
end

class Squares
  def initialize(n)
    @n = n.next
  end

  def square_of_sums
    @n.times.reduce(:+).square
  end

  def sum_of_squares
    @n.times.map(&:square).reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
