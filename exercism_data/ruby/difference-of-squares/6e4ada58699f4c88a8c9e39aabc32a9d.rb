class Squares
  def initialize(natural_number)
    @number = natural_number
  end

  def difference
    return square_of_sums - sum_of_squares
  end

  def square_of_sums
    (1..@number).to_a.inject(:+) ** 2
  end

  def sum_of_squares
    (1..@number).collect { |i| i ** 2 }.inject(:+)
  end
end
