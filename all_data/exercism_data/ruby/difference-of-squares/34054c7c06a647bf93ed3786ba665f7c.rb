class Squares
  def initialize(n)
    @one_to_n = 1 .. n
  end

  def square_of_sums
    @one_to_n.reduce(:+) ** 2
  end

  def sum_of_squares
    @one_to_n.reduce(0) { |sum, natural| sum + natural ** 2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
