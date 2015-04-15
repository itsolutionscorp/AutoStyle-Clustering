class Squares

  def initialize(num)
    @num = num
  end

  def square_of_sums
    result = 0
    (1..@num).each { |num| result += num }
    result ** 2
  end

  def sum_of_squares
    result = 0
    (1..@num).each { |num| result += num ** 2 }
    result
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
