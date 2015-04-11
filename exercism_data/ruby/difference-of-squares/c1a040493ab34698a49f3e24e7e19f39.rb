class Squares

  def initialize(num)
    @num = num
  end

  def square_of_sums

    sum = (1..@num).reduce(:+)**2

  end

  def sum_of_squares
    sum = 0

    (1..@num).each { |n| sum += n**2 }

    sum

  end

  def difference
    square_of_sums - sum_of_squares
  end

end
