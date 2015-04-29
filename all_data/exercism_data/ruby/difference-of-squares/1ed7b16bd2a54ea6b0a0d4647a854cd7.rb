class Squares
  def initialize(number)
    @number = number
  end

  def sum_of_squares
    total = 0
    @number.times { |n| total += (n + 1)**2 }
    total
  end

  def square_of_sums
    total = 0
    @number.times { |n| total += (n + 1) }
    total**2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
