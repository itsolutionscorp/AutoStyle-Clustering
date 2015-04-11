class Squares
  def initialize(sum)
    @sum = sum
  end

  def square_of_sums
    (1..@sum).inject(:+) ** 2
  end

  def sum_of_squares
    (1..@sum).inject { |sum, i| sum + i**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
