class Squares
  def initialize value
    @value = value
  end

  def square_of_sums
    (1..@value).inject(:+)**2
  end

  def sum_of_squares
    (1..@value).collect { |v| v**2 }.inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
