class Squares
	attr_accessor :number

  def initialize(n)
    @number = n
  end

  def square_of_sums
    ((1..@number).inject(:+))**2
  end

  def sum_of_squares
    (1..@number).inject { |sum, x| sum + (x**2)}  
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
