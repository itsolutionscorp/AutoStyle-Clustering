class Squares
  
  #attr_accessor :num

  def initialize(num)
    @num = num
  end

  def square_of_sums
    sums = (@num * (@num + 1))/ 2
    sums * sums
  end

  def sum_of_squares
    sum = 0
    (1..@num).each do |n|
      sum = sum + n*n
    end
    sum
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
