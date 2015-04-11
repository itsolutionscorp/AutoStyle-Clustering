class Squares

  attr_reader :total_num

  def initialize(total_num)
    @total_num = total_num
  end

  def sum_of_squares
    squares = num_array.map {|num| num**2}
    squares.inject(:+)
  end

  def square_of_sums
    sum = num_array.inject(:+)
    sum**2
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def num_array
    (1..total_num)
  end
end
