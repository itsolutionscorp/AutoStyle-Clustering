class Squares
  def initialize(natural_number)
    raise ArgumentError, "You need to provide a natural number (>= 0)" if natural_number < 0
    @natural_number = natural_number
  end

  def square_of_sums
    (1..@natural_number).inject(0) { |sum, n| sum += n } ** 2
  end

  def sum_of_squares
    (1..@natural_number).inject(0) { |sum, n| sum += n**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
