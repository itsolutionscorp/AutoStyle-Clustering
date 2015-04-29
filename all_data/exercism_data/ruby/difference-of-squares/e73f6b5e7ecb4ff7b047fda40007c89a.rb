class Squares
  def initialize(num)
    @num = num
  end

  def square_of_sums
    @sq_of_sm ||= (1..@num).reduce(0) do |sum, n|
      sum + n
    end ** 2
  end

  def sum_of_squares
    @sm_of_sq ||= (1..@num).reduce(0) do |sum, n|
      sum + n**2
    end
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
