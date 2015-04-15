class Squares

  def initialize(num)
    @num = num
  end

  def square_of_sums
    sum = 0
    @num.downto(1) do |n| sum += n end
    sum**2
  end

  def sum_of_squares
    sum = 0
    @num.downto(1) do |n| sum += (n**2) end
    sum
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
