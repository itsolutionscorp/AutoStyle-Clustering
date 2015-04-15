class Squares
  
  def initialize(max_number)
    @max_number = max_number
  end

  def square_of_sums
    (1..@max_number).inject(:+)**2
  end

  def sum_of_squares
    (1..@max_number).inject { |sum, n| sum + n**2 }
  end
  
  def difference
    square_of_sums - sum_of_squares
  end

end
