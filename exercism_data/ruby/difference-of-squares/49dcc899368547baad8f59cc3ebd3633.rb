class Squares
  attr_reader :n
  def initialize(n)
    @n = n
  end

  def square_of_sums
    (1..n).inject(:+) ** 2
  end

  def sum_of_squares
    (1..n).map {|x| x ** 2 }.inject(:+)
  end

  def difference
    (sum_of_squares - square_of_sums).abs
  end
end