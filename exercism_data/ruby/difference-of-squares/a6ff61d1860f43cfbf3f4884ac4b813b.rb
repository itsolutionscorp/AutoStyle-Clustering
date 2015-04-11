class Squares
  def initialize(upper_bound)
    @upper_bound = upper_bound
  end

  def square_of_sums
    (1..@upper_bound).inject(:+) ** 2
  end

  def sum_of_squares
    (1..@upper_bound).map{|i| i ** 2}.inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
