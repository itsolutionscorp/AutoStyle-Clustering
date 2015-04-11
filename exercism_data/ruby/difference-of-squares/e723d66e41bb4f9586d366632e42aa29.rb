class Squares

  def initialize(number)
    @number = number
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def sum_of_squares
    (@number.times.map { |x| x + 1}.reduce(0){ |sum, value| sum + value**2})
  end

  def square_of_sums
    (@number.times.map { |x| x + 1}.reduce(0) { |sum, value| sum + value}) ** 2
  end
end
