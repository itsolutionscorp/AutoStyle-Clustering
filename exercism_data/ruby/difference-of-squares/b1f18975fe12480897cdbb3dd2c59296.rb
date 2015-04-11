class Squares
  def initialize(number)
    @number = number
  end

  def sum_of_squares
    sum = 0
    (1..@number).to_a.each { |n| sum += (n*n) }
    sum
  end

  def square_of_sums
    square = 0
    (1..@number).to_a.each { |n| square+= n }
    square**2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
