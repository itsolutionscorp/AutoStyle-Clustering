class Squares

  def initialize(num)
    @num = num
    @num_array = (1..@num).to_a
  end

  def square_of_sums
    sum = @num_array.inject(:+)
    sum**2
  end

  def sum_of_squares
    sum = 0
    @num_array.each {|num| sum += num**2}
    sum
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
