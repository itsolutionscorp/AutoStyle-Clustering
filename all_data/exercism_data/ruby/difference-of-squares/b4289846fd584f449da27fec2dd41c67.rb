class Squares
  def initialize(num)
    @num = num
  end
  def square_of_sums
    # mathematical calculation for sum of a set of numbers where x is the max number is:
    # x * (x + 1) / 2
    (@num * (@num + 1) / 2) ** 2
  end
  def sum_of_squares
    # mathematical calculation for sum of the squares of a set of numbers where x is the max number is:
    # x * (x + 1) * (2 * x + 1) / 6
    @num * (@num + 1) * (2 * @num + 1) / 6
  end
  def difference
    square_of_sums - sum_of_squares
  end
end
