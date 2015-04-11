class Squares

  def initialize(max)
    @max = max
  end

  def square_of_sums
    (1..@max).to_a.inject(:+) ** 2
  end

  def sum_of_squares
    (1..@max).to_a.inject{|start, current| start + (current ** 2)}
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
