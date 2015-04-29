class Squares
  def initialize (number)
    @number = number
  end

 def sum_of_squares
   sum = (1..@number).inject(0) {|sum, n| sum += n * n}
 end

 def square_of_sums
  sum = (1..@number).inject(0, :+)
  sum * sum
 end

  def difference
    square_of_sums - sum_of_squares
  end

end
