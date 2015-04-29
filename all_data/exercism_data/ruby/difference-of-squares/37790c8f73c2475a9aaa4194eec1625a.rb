class Squares
  attr :array

  def initialize(initial_number)
    @array = (1..initial_number).to_a
  end

  def square_of_sums
    (array.reduce(:+)) ** 2
  end

  def sum_of_squares
    squares_array = array.map { |x| x ** 2 } 
    squares_array.reduce(:+)
  end

  def difference
    (square_of_sums - sum_of_squares).abs
  end

end
