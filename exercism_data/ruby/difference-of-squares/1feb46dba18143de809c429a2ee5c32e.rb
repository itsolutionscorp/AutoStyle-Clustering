class Squares
  def initialize(natural)
    @natural = natural
  end

  def square_of_sums
    (1..@natural).reduce(:+)**2
  end

  def sum_of_squares
    (1..@natural).map {|n| n**2}.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
