class Squares

  def initialize(n)
    @n = n
  end

  def sum_of_squares
    @sum_of_squares ||= (@n*(@n + 1)*(2*@n+1))/6
  end

  def square_of_sums
    @square_of_sums ||= (1..@n).to_a.inject(:+) ** 2
  end

  def difference
    (sum_of_squares - square_of_sums).abs
  end

end
