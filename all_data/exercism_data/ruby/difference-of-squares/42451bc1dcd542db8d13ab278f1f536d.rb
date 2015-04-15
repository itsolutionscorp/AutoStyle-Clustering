class Squares
  def initialize(num)
    @num = num
  end

  def sum_of_squares()
    (1..@num).reduce(0) do |sum, n|
      sum + n**2
    end
  end

  def square_of_sums()
    (1..@num).reduce(:+)**2
  end

  def difference()
    square_of_sums - sum_of_squares
  end
end
