class Squares
  def initialize(n)
    @n = n.next
  end

  def square_of_sums
    @n.times.reduce(:+)**2
  end

  def sum_of_squares
    @n.times.reduce{|sum, i| sum + i**2}
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
