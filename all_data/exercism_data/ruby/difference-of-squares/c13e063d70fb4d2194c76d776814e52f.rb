class Squares
  def initialize(number)
    @num = number
  end

  # (1 + 2 + 3) ** 2
  def square_of_sums 
    sums = 0
    (1..@num).each { |n| sums += n }
    sums ** 2
  end

  # (1**2) + (2**2) + (3**2)
  def sum_of_squares
    sum = 0
    squares = (1..@num).map { |n| n**2 }
    squares.each { |n| sum += n }
    sum
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
