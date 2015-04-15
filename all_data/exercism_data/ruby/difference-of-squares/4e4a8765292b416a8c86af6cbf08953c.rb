class Squares

  def initialize n
    @n = n
  end

  def sum_of_squares
    (1..@n).to_a.map {|x| x * x}.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def square_of_sums
    (1..@n).to_a.reduce(:+)**2
  end
end
