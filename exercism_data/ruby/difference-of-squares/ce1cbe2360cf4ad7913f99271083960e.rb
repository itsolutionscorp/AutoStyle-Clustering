class Squares
  def initialize(num)
    @num = num
  end

  def square_of_sums; (1..@num).reduce(:+)**2 end
  def sum_of_squares; (1..@num).map{|_| _**2}.reduce(:+) end
  def difference; square_of_sums - sum_of_squares end
end
