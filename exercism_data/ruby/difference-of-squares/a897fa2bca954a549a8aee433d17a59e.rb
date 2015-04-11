class Squares
  def initialize(n)
    @n = (1..n).to_a
  end

  def square_of_sums
    @n.reduce(:+) ** 2
  end

  def sum_of_squares
    @n.map { |n| n ** 2 }
      .reduce(:+)
  end

  def difference
    (sum_of_squares - square_of_sums).abs
  end
end
