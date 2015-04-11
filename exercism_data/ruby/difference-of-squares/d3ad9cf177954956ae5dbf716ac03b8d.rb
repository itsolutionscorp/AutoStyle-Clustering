class Squares

  def initialize(number)
   @number = number
  end

  def square_of_sums
    (@number*(@number+1)/2)**2
  end

  def sum_of_squares
    sum = 0
    @number.times do |i|
      sum += (i+1)**2
    end
    sum
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
