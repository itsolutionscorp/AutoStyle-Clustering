class Squares
  def initialize(n)
    @n = n
  end

  def square_of_sums
    sum = 0
    1.upto(@n) { |i| sum += i }
    sum**2 # Returns the square of sums
  end

  def sum_of_squares
    sum = 0
    arr = 1.upto(@n).map { |i| i**2}
    arr.each { |i| sum += i }
    return sum # Returns the sum of squares
  end

  def difference
    square = Squares.new(@n)
    square_of = square.square_of_sums
    sum_of = square.sum_of_squares
    arr = [square_of, sum_of]
    arr.max - arr.min
  end
end
