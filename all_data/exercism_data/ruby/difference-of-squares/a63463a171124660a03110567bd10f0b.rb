class Squares
  def initialize(num)
    @num = num
  end

  def square_of_sums
    out = (1..@num).inject(0, &:+).**2
  end

  def sum_of_squares
    out = (1..@num).to_a.map { |n| n**2 }.inject(0, &:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
