class Squares

  def initialize(integer)
    @integer = integer.to_i
    @int_array = (1..integer)
  end

  def square_of_sums
    @int_array.inject(:+)**2
  end

  def sum_of_squares
    square_array = @int_array.map{|int| int**2 }
    square_array.inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
