class Squares
  def initialize(num)
    @nums = (1..num)
  end

  def square_of_sums
    @nums.inject(0, &:+) **2
  end

  def sum_of_squares
    @nums.reduce(0) {|sum, num| sum + (num * num)}
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
