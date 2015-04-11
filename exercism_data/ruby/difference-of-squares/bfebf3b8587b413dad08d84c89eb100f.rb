class Squares
  def initialize(number)
    @number = number
  end
  def sum_of_squares
    squared_nums = []
    (1..@number).each do |num|
      squared_nums << (num ** 2)
    end
    squared_nums.inject(:+)
  end
  def square_of_sums
    sums = []
    (1..@number).each {|num| sums << num}
    num_to_square = sums.inject(:+)
    num_to_square ** 2
  end
  def difference
    square_of_sums - sum_of_squares
  end
end
