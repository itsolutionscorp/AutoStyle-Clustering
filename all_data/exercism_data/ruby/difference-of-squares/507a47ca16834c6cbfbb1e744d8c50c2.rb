class Squares
  def initialize(number)
    @number = number
  end

  def square_of_sums
    square = 0
    (1..@number).each { |x| square += x}
    return square = square**2
  end

  def sum_of_squares
    sum = 0
    (1..@number).each { |x| sum += x**2 }
    return sum
  end

  def difference
    return (square_of_sums - sum_of_squares).abs
  end

end
