class Squares
  def initialize(n)
    @nums = Array(1..n)
  end

  def square_of_sums
    (@nums.inject { |sum, x| sum + x }) ** 2
  end

  def sum_of_squares
    @nums.inject { |sum, x| sum + (x ** 2)  }
  end

  def difference
    (sum_of_squares - square_of_sums).abs
  end
end
