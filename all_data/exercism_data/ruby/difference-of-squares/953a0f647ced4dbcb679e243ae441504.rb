class Squares

  def initialize(num)
    @num = num
  end

  def square_of_sums
    i = 1
    total = 0
    @num.times {total += i; i += 1} 
    total**2
  end

  def sum_of_squares
    i = 1
    total = 0
    @num.times {total += i**2; i += 1}
    total
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
