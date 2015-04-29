class Squares

  def initialize(number)
    @number = number
  end

  def square_of_sums
    sums = 0
    (1..@number).to_a.each { |n| (sums += n)**2 }
    sums
  end

  def sum_of_squares
    sum = 0
    (1..@number).to_a.each { |n| sum += n**2}
    sum
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
