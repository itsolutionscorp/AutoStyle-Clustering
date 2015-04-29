class Squares
  def initialize(n)
    @number_of_squares = n
  end

  def square_of_sums
    1.upto(@number_of_squares).reduce(&:+)**2
  end

  def sum_of_squares
    1.upto(@number_of_squares).map{ |i| i**2 }.reduce(&:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
