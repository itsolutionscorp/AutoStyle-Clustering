class Squares
  def initialize(val)
    @val = val
  end

  def square_of_sums
    (1..@val).reduce(&:+)**2
  end

  def sum_of_squares
    (1..@val).map{|i| i**2}.reduce(&:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
