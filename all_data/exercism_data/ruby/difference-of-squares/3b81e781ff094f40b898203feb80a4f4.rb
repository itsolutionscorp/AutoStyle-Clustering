class Squares
  def initialize(int)
    @int = int
  end

  def square_of_sums
    (1..@int).reduce(:+)**2
  end

  def sum_of_squares
    (1..@int).reduce{|total, x| total += x**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
