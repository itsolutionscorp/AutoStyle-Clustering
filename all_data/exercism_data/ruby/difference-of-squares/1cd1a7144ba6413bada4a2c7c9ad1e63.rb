class Squares
  def initialize(count)
    @count = count
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def square_of_sums
    1.upto(@count).inject(:+) ** 2
  end

  def sum_of_squares
    1.upto(@count).map { |n| n ** 2 }.inject(:+)
  end
end
