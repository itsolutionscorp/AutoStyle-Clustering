class Squares
  def initialize limit
    @nats = (1..limit)
  end

  def square_of_sums
    @nats.reduce(:+)**2
  end

  def sum_of_squares
    @nats.inject { |sum, n| sum + n**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
