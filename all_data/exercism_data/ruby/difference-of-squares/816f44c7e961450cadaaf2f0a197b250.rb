#define a squares class

class Squares

  def initialize(num)
    @num = num
  end

#make a sum of squares method
  def sum_of_squares
#square each num and add all together
    squared_array = []
    0.upto(@num) {|number| squared_array.push(number)}
    squared_array = squared_array.map { |number| number ** 2}
    squared_array.inject(:+)
  end

#make a square of sums method
  def square_of_sums
    squared_array = []
    0.upto(@num) {|number| squared_array.push(number)}
    squared = squared_array.inject(:+)
    squared**2
  end

#make a difference between the two method
  def difference
    diff = (square_of_sums - sum_of_squares)
  end

end
