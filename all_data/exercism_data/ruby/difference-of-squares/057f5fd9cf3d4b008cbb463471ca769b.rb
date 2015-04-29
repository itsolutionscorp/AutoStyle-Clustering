class Squares
  def initialize(n)
    @nums = (1..n).to_a
  end

  def square_of_sums
    sum = 0
    @nums.each { |num| sum += num }
    sum**2
  end

  def sum_of_squares
    sum = 0
    @nums.each { |num| sum += num**2 }
    sum
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
