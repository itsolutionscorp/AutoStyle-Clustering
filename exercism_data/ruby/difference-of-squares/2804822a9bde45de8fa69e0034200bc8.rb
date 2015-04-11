class Squares

  def initialize(num)
    @num = num
  end

  def sum_of_squares
    sum = 0
    @num.times do |i|
      sum += (i + 1) ** 2
    end
    sum
  end

  def square_of_sums
    sum = 0
    @num.times do |i|
      sum += (i + 1)
    end
    sum * sum
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
