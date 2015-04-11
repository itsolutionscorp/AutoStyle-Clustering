class Squares

  def initialize(natural_number)
    @natural_number = natural_number
  end

  def square_of_sums
    (0..@natural_number).inject{|sum,x| sum + x }**2
  end

  def sum_of_squares
    (0..@natural_number).inject{|sum,x| sum + x**2}
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
