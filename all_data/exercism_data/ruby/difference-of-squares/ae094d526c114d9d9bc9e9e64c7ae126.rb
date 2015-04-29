class Squares
  def initialize(count)
    @count = count
  end

  attr_reader :count

  def square_of_sums
    (1..count).inject(:+) ** 2
  end

  def sum_of_squares
    (1..count).inject(0) { |sum, n| sum + n**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
