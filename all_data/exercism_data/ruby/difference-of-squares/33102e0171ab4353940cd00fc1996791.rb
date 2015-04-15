class Squares

  def initialize(number)
    @number = number
  end


  def sum_of_squares
    (1..@number).to_a.inject(0){|sum, x| sum + x**2}
  end

  def square_of_sums
    (1..@number).to_a.inject(0){|sum, x|  sum + x }**2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
