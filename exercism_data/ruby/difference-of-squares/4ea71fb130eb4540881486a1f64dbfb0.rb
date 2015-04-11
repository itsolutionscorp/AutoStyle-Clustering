class Squares
  attr_reader :count

  def initialize(count)
    @count = count
  end

  def square_of_sums
    (1..count).inject(:+) ** 2
  end

  def sum_of_squares
    (1..count).map {|n| n**2 }.inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
