class Squares

  def initialize(number)
    @number = number
  end

  def square_of_sums
    square = 0
    (1..@number).each { |number| square += number}
    (square ** 2)
  end

  def sum_of_squares
    sum = 0
    (1..@number).each { |number| sum += (number ** 2) }
    sum
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
