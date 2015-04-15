class Squares

  def initialize(number)
    @number = number
  end
  
  def square_of_sums
    (1..@number).to_a.reduce(:+)**2
  end

  def sum_of_squares
    (1..@number).to_a.map {|i| i **2}.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
