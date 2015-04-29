class Squares
  
  def initialize(n)
    @n = n
    @return_value = 0
  end

  
  def square_of_sums
    @return_value = 0
    (1..@n).each {|num| @return_value += num}
    @return_value = @return_value**2
  end

  def sum_of_squares
    @return_value = 0
    (1..@n).each {|num| @return_value += num**2}
    @return_value
  end

  def difference
    square_of_sums - sum_of_squares
  end
  
end
