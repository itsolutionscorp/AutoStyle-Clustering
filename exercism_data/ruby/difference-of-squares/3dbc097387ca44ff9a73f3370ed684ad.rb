class Squares

  def initialize (upto_int)
    @nums = 1..upto_int
  end

  def sum_of_squares
    @nums.inject(0) { |sum, n| sum + n*n }
  end

  def square_of_sums
    @nums.reduce(:+) ** 2
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
