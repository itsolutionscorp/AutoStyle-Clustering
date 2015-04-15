class Squares

  def initialize(limit)
    @limit = limit
  end

  def sum_of_squares
    (1..@limit).to_a.inject {|sum, number| sum + number**2}
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end

  def square_of_sums
    (1..@limit).to_a.inject(:+)**2
  end

end
